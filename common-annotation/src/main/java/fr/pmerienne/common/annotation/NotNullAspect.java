package fr.pmerienne.common.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Cet aspect sert à intercepter les appels aux méthodes possédant un argument
 * avec une annotation {@link NotNull}.
 * 
 * Il lève une {@link IllegalArgumentException} si un des paramètres possédant
 * l'annotation {@link NotNull} est null.
 * 
 * Exemple de configuration aop de spring :
 * 
 * <pre>
 * {@code
 * <beans xmlns="http://www.springframework.org/schema/beans"
 * 	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
 * 	xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
 * 	xmlns:util="http://www.springframework.org/schema/util"
 * 	xsi:schemaLocation="
 *   http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
 *   http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
 *   http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
 *   http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
 *   http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.0.xsd">
 * 
 * 	<context:component-scan base-package="fr.pmerienne.annotations" />
 * 	<aop:aspectj-autoproxy proxy-target-class="true" />
 * </beans>
 * }
 * </pre>
 * 
 * 
 * @author pmerienne
 * @see NotNull
 */
@Aspect
@Component
public class NotNullAspect extends ParameterAspect<NotNull> {

	@Override
	@Pointcut("execution(* *(.., @NotNull (*), ..))")
	public void annotatedMethod() {
	}

	@Override
	protected void processParameter(NotNull paramAnnotation, Class<?> paramType, Object parameter, String paramName) {
		if (parameter == null) {
			throw new IllegalArgumentException(paramName + "must not be null");
		}
	}

	@Override
	protected Class<NotNull> getAnnotationClass() {
		return NotNull.class;
	}
}
