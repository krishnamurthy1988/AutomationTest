# AutomationTest

Steps to execute the Tests in command Prompt:

1. Set environment variable - Export NB_HOME=/home/nanobi/Krishnamurthy/NANOBI_SERVERS/nbconfig_local

2. Place "automation_test_configuration.properties" file into "nbconfig_local" directory.

3. Set the report path, chromedriver path accordingly in "automation_test_configuration.properties" file

4. Set CLASSPATH Variable - Export CLASSPATH=/home/nanobi/Krishnamurthy/Dev_Workspace/AutomationTest/testng-6.9.8.jar.

5. java -cp /home/nanobi/Krishnamurthy/Dev_Workspace/AutomationTest/testng-6.9.8.jar:/home/nanobi/Krishnamurthy/Dev_Workspace/AutomationTest/jcommander-1.48.jar org.testng.TestNG testng.xml
