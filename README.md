![MapleWWW tools](http://digi-area.com/images/light/maplewww/logo.png)

Maple WWW is a technology that brings Maple Worksheets to World Wide Web. Maple WWW is made with Closure IDE.
Maple WWW Tool com.digiarea.maple.www is an Eclipse plugin to prepare Maple Worksheets for Maple WWW.

#### Requirements
 - Eclipse Kepler or higher
 - Java 7 or higher

#### Build

 - Download Eclipse and check out the project
 - Right Click on the project
 - Select **Export**
 - Select **Deployable plug-ins and fregments**
 - Follow the instructions

#### Use

Use the following command line to run the tool:
```bash
java -cp "PATH_TO_FOLDER_WITH_THE_EXPORTED_PLUGIN/*" com.digiarea.maple.www.Converter -src "PATH_TO_FOLDER_WITH_MAPLE_WORKSHEETS" -dst "PATH_TODESTINATION_FOLDER" -url "http://digi-area.com/light/MapleWWW/js/maple-www.js" -kind ANY
```

### Links
 
 - [Maple WWW Website](http://digi-area.com/light/MapleWWW/)
 - [Closure IDE - JavaScript IDE powered by Google Closure Tools](http://digi-area.com/ClosureIDE/)

###License

This Software is licensed under the Eclipse Public License - v 1.0. See the LICENSE file for details.

Except as otherwise noted, the media content (text, images, icons etc.) of the Software is licensed under the 
[Creative Commons Attribution 3.0 License](http://creativecommons.org/licenses/by/3.0/).
