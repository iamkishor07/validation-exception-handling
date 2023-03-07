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
    
    so, here we use validation on the userdto class to validate the data before we push into the DB,
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


 #as we see we get all the exception and those exceptions are
 
   1.MethodArgumentNotValidException
