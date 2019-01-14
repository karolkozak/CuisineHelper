# Cuisine Helper

Application that allows you to choose from list food ingredients, then system suggests you cuisines that are available with provided ingredients.
You can extend data by adding ingredients or cuisines to `cuisines.xdsl` file. It can be easily done with GeNIe Modeler.  

## Technology stack

 - Java8
 - JavaFX
 - Spring Boot
 - Smile (jSmile)

## How to start

1. Download jSmile and license from [bayesfusion](https://download.bayesfusion.com/files.html?category=Academia)
1. Allow gradle to fetch dependencies in your IDE
1. Add jsmile-x.y.z.jar where x.y.z is the version number as library to project
1. Add jsmile.dll to `modules` in `dependencies` tab
1. Open license file for java and copy content
1. Create `smile.License.SmileLicense` class with static method `addLicense()` which returns copied content
1. Run application
