![MapleWWW tools](http://digi-area.com/images/light/maplewww/logo.png)

[Maple WWW](http://digi-area.com/light/MapleWWW/) is a technology that brings Maple Worksheets to the World Wide Web. Maple WWW is made with Closure IDE. 

Maple WWW Tool **com.digiarea.maple.www** is an Eclipse plugin to prepare Maple Worksheets for Maple WWW. See more information in the [Maple WWW Getting Started](http://digi-area.com/light/MapleWWW/getting-started.php).

#### Requirements
 - Eclipse Kepler or higher
 - Java 7 or higher

#### Downloading

Go to the [Releases page](https://github.com/DigiArea/maple-www/releases) and download the latest build.

#### Use

Use running templates included in the released version (MapleWWW.bat for Windows or MapleWWW.sh for Unix).

 - Open a template
 - Replace **PATH_TO_FOLDER_WITH_MAPLE_WORKSHEETS** and **PATH_TO_DESTINATION_FOLDER** with the related paths
 - Save the template and run

#### Command Line

To run the tool from the command line, use the following:
```bash
java -cp "PATH_TO_FOLDER_WITH_THE_EXPORTED_PLUGIN/*" com.digiarea.maple.www.Converter -src "PATH_TO_FOLDER_WITH_MAPLE_WORKSHEETS" -dst "PATH_TO_DESTINATION_FOLDER" -url "URL_OF_MAPLE_WWW_JS" -kind ANY
```

#### Build

 - Download Eclipse and check out the project
 - Right Click on the project
 - Select **Export**
 - Select **Deployable plug-ins and fregments**
 - Follow the instructions

### Links
 
 - [Maple WWW Project](http://digi-area.com/light/MapleWWW/)
 - [Closure IDE - JavaScript IDE powered by Google Closure Tools](http://digi-area.com/ClosureIDE/)

###License

This Software is licensed under the Eclipse Public License - v 1.0. See the LICENSE file for details.

Except as otherwise noted, the media content (text, images, icons etc.) of the Software is licensed under the 
[Creative Commons Attribution 3.0 License](http://creativecommons.org/licenses/by/3.0/).
