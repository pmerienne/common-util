package fr.pmerienne.common.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
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
public class NotNullAspect {

	private final static Logger LOGGER = Logger.getLogger(NotNullAspect.class);

	@Before("execution(* *(@NotNull (*)))")
	public void checkNotNullArgs(JoinPoint joinPoint) {
		try {
			MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
			Object target = joinPoint.getTarget();
			Method targetMethod = target.getClass().getDeclaredMethod(methodSignature.getName(),
					methodSignature.getParameterTypes());
			final Object[] targetObjectMethodArgumentArray = joinPoint.getArgs();
			Annotation[][] parameterAnnotations = targetMethod.getParameterAnnotations();
			int parameterIndex = 0;
			for (final Annotation[] annotations : parameterAnnotations) {
				for (final Annotation annotation : annotations) {
					if (annotation instanceof NotNull) {
						if (targetObjectMethodArgumentArray.length > 0) {
							Object methodArgument = targetObjectMethodArgumentArray[parameterIndex];
							if (methodArgument == null) {
								throw new IllegalArgumentException(((NotNull) annotation).value()
										+ " must not be null.");
							}
						}
					}
					parameterIndex++;
				}
			}
		} catch (NoSuchMethodException e) {
			LOGGER.warn("Unable to check not null argument.", e);
		}
	}
}
