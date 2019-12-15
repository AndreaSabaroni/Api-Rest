package mutante.validaciones;

import java.lang.annotation.*;
import javax.validation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorCadenaAdn.class)
public @interface CadenaAdn {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
