Technical Test:
Part 1:

Tech Used:
Java 17
SpringBoot Plugin 3.2.5 (SpringBoot 6.1.6)
Gradle 8.7

I have made some assumptions that I would drive out the requirements for:
1 - The directory name is not specified, so should be give to the Java application
2 - The header row for the file may or may not be present, I have assumed it is present and the first row is therefore ignored
3 - The directory contain CSV files is to be specified as a property - I have specified this in application.yml
4 - I have also externalised the post and get urls in to a property so that these can be used throughout the code if needed.

Customers with the same id in the file will update the existing record - It would be simple to check if the id is existing before processing the row. I have assumed that the row should simply update first. To keep to the timescales I have implemented the standard Spring behaviour, but would put in a workaround using either generated Id's, or by implementing Persistable and isNew to ensure that the record is not a duplicate and isn't meant to be updated.

I worked in a bottom-up approach, creating the entity, data service and repository, the controller and then working on the client side initially processing the file contents, then locating all of the files in a directory, then processing the files. I then plumbed this through the client testing that data loads in to h2 on startup.

Each set of tests were written to ensure functionality I have written works correctly, not testing that repository code works (as it's not my code) and the basics of the file processing is unit tested correctly.

This took longer than I anticipated, as I had some issues with running the application in eclipse in my final round of testing. This seemed to be down to caching issues (a restart eventually sorted this out). In total I spent about 90-100 minutes on the exercise.