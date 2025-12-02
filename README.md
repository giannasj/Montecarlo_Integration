## Montecarlo Integration

The Montecarlo Integration Method utilizes the sampling of a large number of random points estimate the area below a function. First, the upper and lower bounds of integration are defined, and then the maximum and& minimum values of the function within that domain are calculated. With this information, a sort of 'box' can be created that encapsulates the entire region of integration for the function. Then, a large number of random points are sampled within the box, each one being recorded as 'inside' or 'outside' the region of integration. Those on the inside are further marked as positive or negative depending on their y-value (as areas below the x-axis are treated as negative). Theoretically, as the number of points tends to infinity, the net proportion (accounting for 'negative' points) of points 'inside' the region becomes significant, and multiplying it by the total area of the 'box' yields a highly accurate approximation of the integral.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

