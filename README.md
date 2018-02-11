# Loader_i3a

[![Build Status](https://travis-ci.org/Arquisoft/Loader_i3a.svg?branch=master)](https://travis-ci.org/Arquisoft/Loader_i3a)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/27b04e16c41248d0abad6d5a4ce83911)](https://www.codacy.com/app/jelabra/Loader_i3a?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Loader_i3a&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/Loader_i3a/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Loader_i3a)

# Setup
This program is almost ready to use, but first of all, you need to download [Maven](https://maven.apache.org/).
## Downloading and installing Maven
### Windows
Download Maven from this [link](https://maven.apache.org/download.cgi)
We recommend the binary zip archive of Maven to be downloaded. Once you have downloaded it and unziped it, keep it in a safe place where you know you won't accidently delete it and add the Maven files to yout Path variable. This might sound difficult but it is not, you just have yo navigate (through the windows explorer) to the bin folder inside the files you have just downloaded. Copy the path of this folder by clicking in the navigation bar located in the upper part of the window, right click, and copy.

Now go to Control Panel > System and Security > system and in the left side of the window click on Advanced system settings. Once there, click on **Environment Variables** and, in your user variables, scroll down to Path. Select it and click on edit, then new, paste the path you have just copied in the textfield and save your changes. Now you are ready to execute the app.

Go to the path where you have cloned or downloaded our repository, and execute:
```sh
mvn package
```

### Linux
As everything in Linux, installing Maven is so easy, just issue the following command:
```sh
sudo apt-get install maven
```
*Keep in mind that the package manager (apt in this case) may differ from yours depending on your Linux distro, so you should know your package manager.*


## Preparing the repository files
First of all is generating the .jar file, for that just use the following command that will clean the target folder (if exists), compile the source code, compile the tests, execute the test and build the .jar that holds the application itself.

```sh
mvn package
```

Now you are one step further to execute the application, you just have to move the .jar created file from the target folder one level up, to do that:


### In Windows
```sh
cd target

move Loader_i31a-x.x.jar ../

cd ..
```


### In Linux
```sh
cd target

mv Loader_i31a-x.x.jar ../

cd ..
```

## Using the program
To use the application once you have done the setup, just put the excel file that holds the agents in the same folder where the executable file is and use this command:
```sh
java -jar Loader_i31a-x.x.jar your-excel-file.xlsx
```
So now you have the application working, congratulations and enjoy it!

*Note: the x.x references the release version that you are downloading*



# Authors

* Elena Allegue González (@eleallegue)
* Marcos Álvarez García (@alvarezGarciaMarcos)
* Anamaria Cotorei (@uo251547)
* Cristina Vena Naredo (@cristinavn)

# Last Team Authors

* Gonzalo de la Cruz Fernández (UO244583)
* Oriol Invernón Llaneza (UO245303)
* Adrian Mirón Cao (UO244843)
