This is a JetBrains IntelliJ IDEA Project

Source code is available under the src folder
	Movement AI implementation available under src\ai in the AISteer() method of each movement behavior
	Character Updates are implemented in src/Objects/Creature in the Update() method
	Feel free to explore the rest of the source code. It's a bit overzealous for the scope of this assignment, but I entered a fey mood while making this and here we are
    The Project is implemented in 3D space rather than 2D space, however all current Scenarios use only x and z coordinates (y is 0)

The output of this program is included in the root of the submission, the same location as this readme as CS 330, Dynamic trajectory data.txt

PA2 Update:
    So. I know you said not to overcomplicate things. However, as it would appear, I am incapable of doing so

    The Jar file included in the Executable folder now launches a GUI where you can select from the available scenarios and view a list of the object in the scenario
        I had originally intended to allow for customization of scenario objects through the GUI, but other classes took my time away from that
        There is also no way to create a custom scenario through the gui at the moment. My apologise if you may have been looking forward to trying that
        You can, however, tinker with some of the simulation settings, like max time, time step, and output dimensionality, through the gui

    There is a modified version of the Python Plotting program included in the executable folder which allows for plotting of 3D paths
        This version of the program requires that the dimensions of the output are specified in the first line of the output file
        Running the program through the Jar file should automatically run the plotter program to display a graph of the output
            I believe this only works on windows because of the way that the program calls the Python script, but I could be wrong
        There is a 3D Demo Scenario included with the program to see how the 3D outputs look

    The included output file is correctly formatted for use with the standard plotter, however outputs from the Jar file may have to use the included plotter
        2D plots can simply have the first line of their outputs removed to work properly
        3D Plots require the included plotter because the standard plotter is not set up to handle their output
