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
