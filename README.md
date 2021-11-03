# RPandaL 
## Author: [Raphael Dray](https://www.linkedin.com/in/raphaeldray/)

RPandaL is a **RPL Calculator** used as a Server.

You can perform different types of operations on different objects like so:
* **Numbers**
* **Complexes**
* **Vectors**

## Usage:

Run the server by launching the main class or the jar:
```bash
java -jar RPandaL.jar

```

The server will start listening on localhost on port 20000.
Then you will have to connect to the server using Netcat for example:
```bash
nc 127.0.0.1 20000
```

You can perform those actions:
* Enter a Number (i.e. **42**)
* Enter a Vector of dimensions N (i.e. **[5, 6, 7, 8]**)
* Enter a Complex (i.e. **5i** _or_ **5 + 5i** _or_ **i**...)

Then this will be pushed in the stack after entering. You can then perform operations like:
* **\+**
* **\-**
* <b>*</b>
* **/**
* **%**