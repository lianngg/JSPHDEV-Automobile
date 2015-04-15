# JSPHDEV-Automobile
**Course project from Java for Smartphone Development, Spring 2015**

This is a car configuration application with client-server architecture implemented in Java.

The final UML diagram is as follows:

Client

![](https://github.com/lianngg/JSPHDEV-Automobile/blob/master/18-641%20Project1Unit5Client/Project1Unit5Client.png)

Server

![](https://github.com/lianngg/JSPHDEV-Automobile/blob/master/18-641%20Project1Unit5Server/Project1Unit5Server.png)


# Lesson learned

HW1:
- IDEs can automatically generate getter and setter functions as well as toString().
- It’s a bad idea to use String and String concatenation in toString function. Use StringBuilder instead to save memory usage and performance.
- Separating class files in different packages is an additional support for encapsulation.

HW2:
- If the array size is initialed to be fixed, it’s important to handle the empty space in the array and check whether the usage exceeded the array size.
- Eclipse supports automatic generation of toString() function with StringBuilder to prevent string concatenations.

Project1 Unit1:
- When dealing with Serialization and deSerialization, the target class and the classes contained in it need to implement Serializable interface.
- The Serial Version ID is used when serializing and deSerializing an object. Java recognizes if the bytes to deSerialize match the local class version. If not, it will throw an exception.
- If you don’t want some of the members to be serialized, declare it static or transient and it will not be included during Java serialization process.
- Static variables belong to the class rather than the object hence they are not saved during Java Serialization process.
- The transient keyword in Java is used to indicate that a field should not be serialized. 
- Serialized object can be transferred via network because Java serialized object remains in form of bytes which is portable.
- Protected functions prevent themselves being called by classes from other packages. In order to accesses the protected functions, the outer class(Automotive class in the unit) should implement the public functions and be in charge of communicate with other classes.
- The advantage of reading files and resources in a single pass without using intermediary buffering is that it reduces I/O overhead and save memory for saving temporary data.
- The disadvantage of reading files in a single pass is that it’s more difficult to parse the data because we have no ideas about how much data is in the resource hence we are unable to initial the size of data structure before hand if the size is not specified in the resource.

Project1 Unit2:
- Making the Automobile object static in ProxyAutomotive class is the practice of singleton design pattern.
- Prevent to use static variables and methods as possible. Use it when implement singleton like LinkedHashMap of automobiles in this unit to act as a database.
- Use an empty class as an API and provide access for users. A proxy class would have all the implementation details of that API including interface functions.
- LinkedHashMap differs from HashMap in that elements are in the order where the entries were put into the map.
- As elements in ArrayList are added to it, its capacity grows automatically. We don’t need to worry about the detail.
- An enum type is a special data type that enables for a variable to be a set of predefined constants. It is useful to define exceptions and errors.
- Use java.util.Date to obtain system time and java.text.DateFormat to set the format.
- The advantage of creating an abstract class that only contains interface method implementations is that it makes the codes extensible and manageable. The abstract class that contains all the implementations of interfaces can not be instantiated but can be inherited by the classes that need the functionalities of those interfaces.

Project1 Unit3:
- When implementing multi-thread, it’s better to implement Runnable rather than extend Thread because it keeps codes loosely coupled.
   Also, we just want something for it to run and don’t need the inheritance from the whole Thread class.
- Use synchronized methods or synchronized objects to prevent data corruption when multi-threads running at the same time.
- It’s very convenient to extend APIs when empty class BuildAuto is used for extensibility and the implementation details are in abstract ProxyAutomobile.
- LinkedHashMap is not thread-safe and might be corrupted if not handled properly. The alternative way is to use synchronizedMap if you don’t want to handle the low-level issues.
- Thread.sleep() makes the current thread to stop for some milliseconds. It’s useful for debugging.
- Threads share all objects and arrays in heap but have their own stack.
- Relying on synchronized to coordinate access between threads might lead to performance issues that affect application scalability. Be careful to make things synchronized!
- Java's monitor supports two kinds of thread synchronization: mutual exclusion and cooperation.
- Mutual exclusion, which is supported in JVM via object locks, enables multiple threads to independently work on shared data without interfering with each other.
- Cooperation, which is supported in JVM via the wait and notify methods of class Object, enables threads to work together towards a common goal.

Project1 Unit4:
- In Thread class, the method start() and run() differs in that start() will create a new thread to execute its overridden run() method while run() only execute its overridden run() method in the same thread that called it.
- Properties store the configurable parameters, which are pairs of strings, one storing the name of the parameter and the other storing the value.
- serverSocket.accept() will block the program until a new client comes in.
- The strategy of building a multi-thread environment is to make the server keep listening to its socket and create a new thread to handle client request after obtaining client’s Socket from accept() method.
- When use PrintWriter, set auto flush in its constructor only guarantee println(), printf() and format() to flush its buffer automatically. You need to manually flush the buffer when use print().
- There are two communication protocols that one can use for socket programming: datagram communication and stream communication.
- The datagram communication protocol, known as UDP (user datagram protocol), is a connectionless protocol. You need to send the local socket descriptor and the receiving socket's address each time a communication is made.
- The stream communication protocol is known as TCP (transfer control protocol), which is a connection-oriented protocol. A connection first be established between two sockets and it can be used to transmit data in both directions.
- Create the ObjectOutputStream before the ObjectInputStream, or remove the timing dependency between constructing them can prevent unexpected blocking and deadlock.

Project1 Unit5:
- Tomcat provides build-in multi thread environment so we do not need to handle threads in the programs.
- Servlet can make a post request to itself and handle it in the doPost method so that we can get data from a form and process the output.
- Servlet can forward the request to JSP so that we can use HTML combined with some Java codes by using <% %> in JSP page.
- We can define welcome file list in web.xml deployment descriptor by using the <welcome-file-list> tag
- Form can be submit by HTTP GET, which embedded input in the URL, or by HTTP POST, which hide the information in the body of the HTTP request.

Project1 Unit6:
- When connect to MySQL database, one can specify the database name after the port number to indicate the access to the database.
- It’s convenient to implement update() by combining delete() with save() but the overhead is heavier than just update a certain field in the database.
- The composition relationship between classes can be realized by using foreign key ON DELETE CASCADE so that it will die with its parent.
- Use AUTO_INCREMENT in MySQL to generate serial number as primary key automatically.
- Prevent storing same information in different table for normalization.
- Database normalization is the process of organizing the attributes and tables to minimize data redundancy.
