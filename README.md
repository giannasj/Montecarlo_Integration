## James Giannasi - 12/02/2025 - Montecarlo Integration

The Montecarlo Integration Method utilizes the sampling of a large number of random points estimate the area below a function. First, the upper and lower bounds of integration are defined, and then the maximum and& minimum values of the function within that domain are calculated. With this information, a sort of 'box' can be created that encapsulates the entire region of integration for the function. Then, a large number of random points are sampled within the box, each one being recorded as 'inside' or 'outside' the region of integration. Those on the inside are further marked as positive or negative depending on their y-value (as areas below the x-axis are treated as negative). Theoretically, as the number of points tends to infinity, the net proportion (accounting for 'negative' points) of points 'inside' the region becomes significant, and multiplying it by the total area of the 'box' yields a highly accurate approximation of the integral.

## VS Code Settings
  - Requires a JDK Runtime of 21 or later
  - Utilizes JavaFX SDK 25.0.1
      - Path to this file can be changed in the launch.json file
  - Extensions
      - Debugger for Java
      - Extension Pack for Java
      - JavaFX Support
      - Language Support for Java
      - Project Manager for Java
      - Test Runner for Java
  - src and lib files have been provided, but a bin folder should be created

## Changing the Integration
Changing the function that is being integrated is done in the Function.java file under the evaluateAt method.
Changing the bounds of integration is done in the Main.java file in the initialization of the static 'function' variable. 
Changing the delay between keyframes in the graph animations can be done in the homeController (for the random sampling graph) and the valuesController (for the value graph). Look for the section with the timeline and there will be a comment directing you to the line where the delay can be changed.

