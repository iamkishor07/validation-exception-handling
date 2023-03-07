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
    we use the following annotations to validate the data and to put respective messages when its get triggerd the following message displays as a result the user         knows what mistake he done while giving the data
    
    #Common Validation Annotations
    
    Some of the most common validation annotations are:

    @NotNull: to say that a field must not be null.
    
    @NotEmpty: to say that a list field must not empty.
    
    @NotBlank: to say that a string field must not be the empty string (i.e. it must have at least one character).
    
    @Min and @Max: to say that a numerical field is only valid when it’s value is above or below a certain value.
    
    @Pattern: to say that a string field is only valid when it matches a certain regular expression.
    
    @Email: to say that a string field must be a valid email address.
