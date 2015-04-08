- Driver.java contains a main function that tests the EditOptions API with two threads running at the same time.

- In EditOptions.java, you can use constructors to setup the properties you are going to modify, and then create a thread to run the process

- This function can be accessed through IEditOptions API using BuildAuto object.

- The implementation of the API is in the abstract class ProxyAutomobile.java

- Methods in Automobile.java are all synchronized to ensure that just single thread can access the function for preventing data corruption.

- EditOptions inherits from ProxyAutomobile to obtain the access to the static LinkedHashMap.