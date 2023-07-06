@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  samples startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and SAMPLES_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\samples.jar;%APP_HOME%\lib\openai-0.1-SNAPSHOT.jar;%APP_HOME%\lib\openai-api.jar;%APP_HOME%\lib\converter-scalars-2.9.0.jar;%APP_HOME%\lib\adapter-rxjava2-2.9.0.jar;%APP_HOME%\lib\converter-gson-2.9.0.jar;%APP_HOME%\lib\retrofit-2.9.0.jar;%APP_HOME%\lib\okhttp-4.9.0.jar;%APP_HOME%\lib\rxandroid-2.1.1.aar;%APP_HOME%\lib\rxjava-2.2.14.jar;%APP_HOME%\lib\azure-ai-openai-1.0.0-beta.1.jar;%APP_HOME%\lib\okio-jvm-2.8.0.jar;%APP_HOME%\lib\kotlin-stdlib-1.4.10.jar;%APP_HOME%\lib\azure-core-http-netty-1.13.3.jar;%APP_HOME%\lib\azure-core-1.39.0.jar;%APP_HOME%\lib\reactor-netty-http-1.0.28.jar;%APP_HOME%\lib\reactor-netty-core-1.0.28.jar;%APP_HOME%\lib\reactor-core-3.4.27.jar;%APP_HOME%\lib\reactive-streams-1.0.4.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.4.10.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\azure-json-1.0.1.jar;%APP_HOME%\lib\jackson-core-2.13.5.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.13.5.jar;%APP_HOME%\lib\jackson-databind-2.13.5.jar;%APP_HOME%\lib\jackson-annotations-2.13.5.jar;%APP_HOME%\lib\slf4j-api-1.7.36.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.89.Final.jar;%APP_HOME%\lib\netty-resolver-dns-native-macos-4.1.89.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-classes-macos-4.1.89.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.89.Final.jar;%APP_HOME%\lib\netty-handler-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.89.Final.jar;%APP_HOME%\lib\netty-codec-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.89.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-transport-native-kqueue-4.1.89.Final-osx-x86_64.jar;%APP_HOME%\lib\netty-transport-classes-epoll-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-classes-kqueue-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.89.Final.jar;%APP_HOME%\lib\netty-transport-4.1.89.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.89.Final.jar;%APP_HOME%\lib\netty-tcnative-boringssl-static-2.0.56.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.89.Final.jar;%APP_HOME%\lib\netty-common-4.1.89.Final.jar;%APP_HOME%\lib\netty-tcnative-classes-2.0.56.Final.jar


@rem Execute samples
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SAMPLES_OPTS%  -classpath "%CLASSPATH%" io.github.kongpf8848.samples.Main %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SAMPLES_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SAMPLES_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
