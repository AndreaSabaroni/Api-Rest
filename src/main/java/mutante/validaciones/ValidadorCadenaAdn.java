package mutante.validaciones;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorCadenaAdn implements ConstraintValidator<CadenaAdn, List<String>> {
	private static final String CARACTERES_VALIDOS = "ATCG";

	private String mensaje;

    @Override
    public void initialize(CadenaAdn cadenaADN) {

        mensaje = cadenaADN.message();

    }

	@Override
	public boolean isValid(List<String> cadenaAdn, ConstraintValidatorContext context) {

		if ((cadenaAdn.isEmpty() || !esADNValido(cadenaAdn))) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensaje).addConstraintViolation();

			return false;
		}

		return true;
	}

	private boolean esADNValido(List<String> dnaMutant) {
		int longitudCadena = dnaMutant.get(0).length();
		if (dnaMutant.size() != longitudCadena)
			return false;

		for (String adn : dnaMutant) {
			if (adn.length() != longitudCadena)
				return false;

			if (!esCadenaDeADNValida(adn.toUpperCase()))
				return false;
		}
		return true;

	}

	private boolean esCadenaDeADNValida(String adn) {
		if (adn == null || adn.isBlank() || adn.length() < 4)
			return false;
		for (int i = 0; i < adn.length(); i++) {
			if (CARACTERES_VALIDOS.indexOf(adn.charAt(i)) < 0)
				return false;
		}
		return true;
	}

}
