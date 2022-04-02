package net.chrisbay.springboottemplate.models.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = NotCommonPasswordValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface NotCommonPassword {

    String message() default "{net.chrisbay.springboottemplate.models.user.NotCommonPassword.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
