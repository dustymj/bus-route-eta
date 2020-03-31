# bus-route-eta

Bus Route ETA Calculator

Description: This is a simple program to calculate the ETA of the next bus arrival given the bus route, bus stop, and route direction. If the user does not enter in the route,
stop, and direction, then they will be prompted for their inputs. If they enter in some number of inputs that's not 0 or 3 then the program will prompt the user for the expected
inputs.

Setup
* Code can be found on GitHub: https://github.com/dustymj/bus-route-eta
* Clone the repo to the directory of your choosing.

How-To-Run
* Compile the program
    - Command Line (probably the best way for portability reasons)
        * The repo should come with the maven wrapper by default (which is super nice, since then you don't have to have maven installed locally)
        * From the directory to which you cloned the project to (so bus-route-eta is the current directory)
            - Windows: Run the command "mvnw.cmd clean install" to compile the program and run the unit tests for it (scroll up through the logs to see tests are ran)
            - Mac / Linux: Run the command "./mvnw clean install" to compile the program and run the unit tests for it (scroll up through the logs to see tests are ran)
    - From IDE (development was done using IntelliJ) with Maven installed
        * Import the project as a new maven project.
        * Using the IDE, run the maven goals "clean" then "install".
* Run the program
    - From the bus-route-eta directory, "cd" into the "target" directory
    - Run the command, "java -jar bus-route-eta-0.0.1-SNAPSHOT.jar" plus whatever arguments you want to provide (or, not, and use the built in prompts)

How-To-Use
* Inputs:
    - No Inputs: The program will prompt the user for each input as they come.
    - Three Inputs: The program will take the three inputs and use them as the route, stop, and direction; respectfully.
    - Other Inputs: The program will prompt the user for the correct number of inputs.

Features
* Before the program will tell you when the next transit arrival will be, it will perform a series of validations.
    - It will validate that the route entered is a valid route.
    - It will validate that the direction entered is a valid direction for that route.
    - It will validate that the stop entered is a valid stop for that route and direction.
* If the transit has reported its accurate location recently, then the output verbiage will be along the lines of, "Transit will arrive in X minutes."
* If the transit has not reported its accurate location recently, then the output verbiage will be along the lines of, "Transit is scheduled to arrive in X minutes"
* If there are no more transits for the current day, then the output will be, "Unfortunately, no more pickups are scheduled for this stop for the rest of the day"

--- End User Section ---

Development:

Development was done in the Java programming language as it is the language I am most comfortable using at this time. Unit tests were written in Groovy with the Spock framework.
I appreciate the Spock framework for unit tests as I feel it makes the tests more readable and lends itself to the flow,
"GIVEN this set of information, WHEN this is called THEN these methods will also be called AND these assertions will be true". It's also a data driven approach as you explicitly
state what you expect the data setup to be when the method is called. Also, Spock allows you to Mock interfaces so you can focus unit testing on solely the current class and method.

* Design:
    - Right off the bat, I just want to air my dirty laundry: everything is done in static methods, and I agree, I hate it. My initial idea was to use spring and have that wire
      everything together so I could have nice interfaces to call into later during testing. However, getting spring set up soon proved to take up more time than I felt I should
      be allotting to it, given the number of things I wanted to do for this program. Plus, I figured it would be better to say, "here's a program that actually works and has
      substance to it" rather than saying, "I spent a week trying to get this spring app working and this is as far as I got".
        * The main dilemma with using spring was trying to figure out how to wire the beans within "public static void main". I wanted that to be my main entry point because this
          program is ultimately going to be super light in nature. However, most of the Spring examples that I found had PSVM simply spinning up an application context and letting
          various controllers provide input to the program. The other alternative was to create an inner main object within PSVM and after spinning up the application context call
          into that object to start the program, but it was at that point that I realized Spring is a great framework for getting a web app up and off the ground and that I'm not
          building a web app at the moment. So...now that I'm two days down, what is left to be the path of least resistance? Unfortunately, that ended up being static
          methods everywhere.
            - The alternative was creating empty objects for each class and then calling the various methods from those classes. However, I fundamentally disagreed with this
              approach since it was highly inaccurate to what was going on. 1.) Do you need an object to call the desired method? No. 2.) Should / will the behavior be the same
              given the input? yes. So, technically the criteria was met for static methods everywhere. Still gross, but, "technically" not wrong either?

    - The other downside to static methods everywhere was it really strapped how much testing I wanted to do and the manner in which I went about it.
        * In a perfect world, for a given Spec for a class, you would spin up an instance of that class and then Mocks for all other interactions. However, since all the methods
          were static, the mocking support is very low. So...next to no mocking happened. And since no mocking happened, the tests are actually performing rest calls instead of
          letting their return values get mocked. Which kind of makes me sad because it's not how I like to do things, but, given time constraints, it was the best I could do.
          Dear Metro Transit...sorry for the 30 REST calls I spammed you while installing...
        * The other downside to not being able to do Mocks is that not everything was able to be tested. Again, airing this out because I'm owning up to it, it's not usually how
          I like to go about doing things. The test support for TimepointDeparture, RestConnection, and BusRouteEtaApplication are sorely lacking because they all required the
          call to get the next timepoint departure. When unit tests are written, I firmly believe that they should be idempotent. The calls for getting routes, directions for
          those routes, and the stops along those routes are idempotent, so legit calls can be made. However, the next transit stop retrieved now is not the same transit stop that
          will be retrieved in 20 minutes. This is the perfect example of where mocking is brilliant because you'd tell the test what you're expecting back (for the given
          scenario).

    - Otherwise, I'm fairly pleased to the overall layout of the project. High level services for the InputHandler, InputValidation, RestConnection, and TimepointDeparture. With a
      sub-package for the three validations. A separate data package was created to house the VOs and, even though the contents are the same between DirectionVO and StopVO, and
      they could have used a shared object at the end of the day, I felt it important that each have their own VO in order to lend proper context to their existence.
