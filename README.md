# validation-exception-handling
 Spring Request validation & Exception Handling Realtime example
 
 
 we defined our userdto as follows
 
         @Data
         @NoArgsConstructor
         @AllArgsConstructor
         public class Userdto {
             private  String  name;
             private  String email;
             private  String mobile;
             private  String gender;
             private  int age;
             private  String nationality;
         }

 
 when we try to post the below data our application takes the details even though they arenot valid and we didn't get any error

        {
               "name": "",
               "email": "@gmail.com",
               "mobile": "99449988888888333",
               "gender": "male",
               "age": 266,
               "nationality": "Indian"
           }
    
    
  #so, here we use validation on the userdto class to validate the data before we push into the DB,
    we use the following annotations to validate the data and to put respective messages when its get triggerd the
    following message displays as a result the user knows what mistake he done while giving the data
    
    #Common Validation Annotations
    
    Some of the most common validation annotations are:

    @NotNull: to say that a field must not be null.
    
    @NotEmpty: to say that a list field must not empty.
    
    @NotBlank: to say that a string field must not be the empty string (i.e. it must have at least one character).
    
    @Min and @Max: to say that a numerical field is only valid when it’s value is above or below a certain value.
    
    @Pattern: to say that a string field is only valid when it matches a certain regular expression.
    
    @Email: to say that a string field must be a valid email address.
    
    
  #@Validated and @Valid
    In many cases, however, Spring does the validation for us. We don’t even need to create a validator object ourselves.
    Instead, we can let Spring know that we want to have a certain object validated. 
    This works by using the the @Validated and @Valid annotations.

    The @Validated annotation is a class-level annotation that we can use to tell Spring to validate parameters that are 
    passed into a method of the annotated class. We’ll learn more about how to use it in the section about
    validating path variables and request parameters.

   We can put the @Valid annotation on method parameters and fields to tell Spring that we want a method parameter or field to be validated
    
    
    #An example of such a class would look like this:

          @Data
          @NoArgsConstructor
          @AllArgsConstructor
          public class Userdto {
              @NotNull(message = "UserName shouldn't be Null or Empty")
              private  String  name;
              
              @Email(message = "Invalid email address")
              private  String email;
              
              @NotNull
              @Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered") private  String mobile;
              
              private  String gender;
              
              @Min(16)
              @Max(90)
              private  int age;
              
              @NotBlank
              private  String nationality;
          }
          
          
          
     For the above userdto class with validations ,when we insert/give the json value as follows
     
      when we try to post the below data our application takes the details even though they arenot valid and we didn't get any error

         {
                "name": "",
                "email": "@gmail.com",
                "mobile": "99449988888888333",
                "gender": "male",
                "age": 266,
                "nationality": "Indian"
            }
            
      #We do get an error's of invalid fields as we mention in our dto class as the exception that look like the below
      
      .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.
      MethodArgumentNotValidException: Validation failed for argument [0] in 
      public org.springframework.http.ResponseEntity<com.example.validationexceptionhandling.Entity.User> 
      com.example.validationexceptionhandling.controller.UserController.Saveuser(com.example.validationexceptionhandling.dto.Userdto) with 3 errors:
      [Field error in object 'userdto' on field 'email': rejected value [@gmail.com]; 
      codes [Email.userdto.email,Email.email,Email.java.lang.String,Email];arguments 
[org.springframework.context.support.
    DefaultMessageSourceResolvable: 
      codes [userdto.email,email]; arguments []; 
      default message [email],[Ljakarta.validation.constraints.Pattern$Flag;@1459063,.*]; default message [Invalid email address]]
      [Field error in object 'userdto' on field 'age': rejected value [266]; codes [Max.userdto.age,Max.age,Max.int,Max]; arguments [org.springframework.context.support.
      DefaultMessageSourceResolvable: 
            codes [userdto.age,age]; arguments []; default message [age],90]; default message [must be less than or equal to 90]] 
            [Field error in object 'userdto' on field 'mobile': rejected value [99449988888888333];
            codes [Pattern.userdto.mobile,Pattern.mobile,Pattern.java.lang.String,Pattern]; 
 arguments [org.springframework.context.support.
      DefaultMessageSourceResolvable: 
          codes [userdto.mobile,mobile]; arguments []; default message [mobile],[Ljakarta.validation.constraints.Pattern$Flag;@6c11d7ea,^\d{10}$];
          default message [Invalid mobile number entered]] ]


 #as we see we get all the exception and those exceptions are MethodArgumentNotValidException
   
   
   ## We therefore use the execptions mechanism to handle the exceptions
   
   Then we create an package of exception in handle the MethodArgumentNotValidException exception, for that we need to create a class as below
   
         @RestControllerAdvice
      public class CustomExceptionHandler {

          @ResponseStatus(HttpStatus.BAD_REQUEST)
          @ExceptionHandler(MethodArgumentNotValidException.class)
           public Map<String,String>handleInvalidException(MethodArgumentNotValidException ex){

              Map<String,String> errormap=new HashMap<>();
              ex.getBindingResult().getFieldErrors().forEach(error->{
                  errormap.put(error.getField(),error.getDefaultMessage());
              });
              return errormap;
          }
      }
      
      
    #The above class that handles the MethodArgumentNotValidException by using the @ExceptionHandler to map the exception to methods and that 
    triggers when particular exception raises and executes the below code.
    
    # In the handleInvalidException method we fetch all the exception that occur from runtime from ex and 
    extract all the field errors(key ,value as pair) like field name and the default message and map into a hashmap and we return to the controller
    that displays those message at the user end.
    
    
   #for the below data when we try to pass
   
          {
               "name": "",
               "email": "@gmail.com",
               "mobile": "666",
               "gender": "male",
               "age": 292,
               "nationality": ""
           }
  #AS we see in the above fields the data is not valid so we get the following message after handling execptions
  
        {
          "nationality": "must not be blank",
          "mobile": "Invalid mobile number entered",
          "age": "must be less than or equal to 90",
          "email": "Invalid email address"
      }
   
   
   So, we can handle any execption by mapping it to the specified method int the exception package and class that is annoated with @RestControllerAdvice
