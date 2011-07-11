package fr.pmerienne.common.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LowerThanAspect extends ParameterAspect<LowerThan> {

	@Override
	@Pointcut("execution(* *(.., @LowerThan (*), ..))")
	public void annotatedMethod() {
	}

	@Override
	protected void processParameter(LowerThan paramAnnotation, Class<?> paramType, Object parameter, String paramName) {
		if ((paramAnnotation.orEquals() && paramAnnotation.value() < (Integer) parameter)
				|| (!paramAnnotation.orEquals() && paramAnnotation.value() <= (Integer) parameter)) {
			String errorMsg;
			if (paramAnnotation.orEquals()) {
				errorMsg = paramName + " must be lower or equals than " + paramAnnotation.value();
			} else {
				errorMsg = paramName + " must be lower than " + paramAnnotation.value();
			}
			throw new IllegalArgumentException(errorMsg);
		}
	}

	@Override
	protected Class<LowerThan> getAnnotationClass() {
		return LowerThan.class;
	}

}
