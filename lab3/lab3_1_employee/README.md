# Lab 3_1 Employee manager example

## Review questions
<br>

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

-    assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail()) 

-    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName())

- assertThat(found).extracting(Employee::getName).containsOnly("bob")  

<br>

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database). 

``` java
    @Mock( lenient = true)
        private EmployeeRepository employeeRepository;

        @InjectMocks
        private EmployeeServiceImpl employeeService;

        @BeforeEach
        public void setUp() {

            //these expectations provide an alternative to the use of the repository
            Employee john = new Employee("john", "john@deti.com");
            john.setId(111L);

            Employee bob = new Employee("bob", "bob@deti.com");
            Employee alex = new Employee("alex", "alex@deti.com");

            List<Employee> allEmployees = Arrays.asList(john, bob, alex);

            Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
            Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
            Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
            Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
            Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
            Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
        }
```
<br>

### c) What is the difference between standard @Mock and @MockBean?

The @Mock annotation is used in unit tests to mock dependencies for the class under test while the @MockBean is used in integration tests to mock Beans (only used in spring application context).

@Mock is used to create a mock object of a class that is used as a dependency of the class under test.

@MockBean is used in integration testing with Spring Boot to create a mock of a bean that is defined in the application context.

<br>

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The "application-integrationtest.properties" file is typically used in integration testing with Spring Boot. This file contains properties specific to the integration test environment and is used to configure the application context for testing.

The default behavior is to use the "application.properties" file to configure the test application context, however when you need to configure specific properties for testing or when you want to use a different set of properties than the ones used in the production environment you can use the "application-integrationtest.properties".

<br>

### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?   

Relatively to the strategy C, the main difference is that this one is a unit test, so it only focuses on the Controller behavior, while D and E are integration tests, thus their focus is the complete application actions in a whole.

Furthermore, between D and E the main difference is that D one accesses the server context through a special testing servlet (MockMvc object), while in E the requester is a REST client (TestRestTemplate).

