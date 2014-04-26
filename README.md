![MapleWWW tools](http://digi-area.com/images/light/maplewww/logo.png)

maple-www
=========

MapleWWW tool com.digiarea.maple.www - is an Eclipse plugin.

Use Eclipse with Java 7 to build it: 
- right click on the project
- select: "Export"
- select: "Deployable plug-ins and fregments"
- follow the instructions

Use the following command line to run:
java -cp "PATH_TO_FOLDER_WITH_THE_EXPORTED_PLUGIN/*" com.digiarea.maple.www.Converter
-src "PATH_TO_FOLDER_WITH_MAPLE_WORKSHEETS"
-dst "PATH_TODESTINATION_FOLDER"
-url "http://digi-area.com/light/MapleWWW/js/maple-www.js"
-kind ANY


###License

This Software is licensed under the Eclipse Public License - v 1.0. See the LICENSE file for details.

Except as otherwise noted, the media content (text, images, icons etc.) of the Software is licensed under the 
[Creative Commons Attribution 3.0 License](http://creativecommons.org/licenses/by/3.0/).
