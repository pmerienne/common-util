package fr.pmerienne.common.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreaterThanAspect extends ParameterAspect<GreaterThan> {

	@Override
	@Pointcut("execution(* *(.., @GreaterThan (*), ..))")
	public void annotatedMethod() {
	}

	@Override
	protected void processParameter(GreaterThan paramAnnotation, Class<?> paramType, Object parameter, String paramName) {
		if ((paramAnnotation.orEquals() && paramAnnotation.value() > (Integer) parameter)
				|| (!paramAnnotation.orEquals() && paramAnnotation.value() >= (Integer) parameter)) {
			String errorMsg;
			if (paramAnnotation.orEquals()) {
				errorMsg = paramName + " must be greater or equals than " + paramAnnotation.value();
			} else {
				errorMsg = paramName + " must be greater than " + paramAnnotation.value();
			}
			throw new IllegalArgumentException(errorMsg);
		}
	}

	@Override
	protected Class<GreaterThan> getAnnotationClass() {
		return GreaterThan.class;
	}

}
