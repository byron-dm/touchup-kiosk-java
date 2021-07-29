# Self Serve Kiosk

---

Self Serve Kiosk is a kiosk application for TouchUp Auto Care

## Architecture Notes

Kiosk uses the Java Platform version 16+ and the following open-source libraries:

* Gradle (https://gradle.org)
* Guice (https://github.com/google/guice)
* JUnit (https://github.com/junit-team/junit5/)
* Log4j (https://logging.apache.org/log4j/2.x/)
* MigLayout (https://github.com/mikaelgrev/miglayout)
* mvvmFX (https://github.com/sialcasa/mvvmFX)

### Usage

First, make sure Java v16+ is correctly installed on your system. Then, run the following command from within the root project folder:
* gradlew run

To generate native installers (currently only Windows is supported):
* Install the latest production version of the Wix Toolset (https://wixtoolset.org/releases/)
* From the run project folder run: "gradlew clean jpackage".    

A Windows executable and a MSI installer will be generated inside the "build/jpackage" folder