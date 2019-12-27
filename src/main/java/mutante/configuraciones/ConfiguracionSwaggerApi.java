package mutante.configuraciones;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.spring.web.paths.RelativePathProvider;

@Configuration
//@EnableJpaRepositories( entityManagerFactoryRef = "entityManagerFactory",
//basePackages = {"mutante.repositorios" },
//transactionManagerRef="transactionManager"
//)

public class ConfiguracionSwaggerApi {

	private RelativePathProvider generarPathProvider(ServletContext servletContext) {

		return new RelativePathProvider(servletContext) {
			@Override
			public String getApplicationBasePath() {
				return "/api-mutante";
			}
		};
	}


}
