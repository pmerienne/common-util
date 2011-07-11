package fr.pmerienne.common.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

public abstract class ParameterAspect<T extends Annotation> {

/**
	 * Redefine like :
	 * 
	 * {@code 
	 * @Pointcut("execution(@com.xxx.xxx.annotation.MyAnnotationForMethod * *(.., @com.xxx.xxx.annotation.MyAnnotationForParam (*), ..))")
	 * public void annotatedMethod()
	 * }
	 */
	public abstract void annotatedMethod();

	@Before("annotatedMethod()")
	public void process(final JoinPoint jp) {
		final Signature signature = jp.getSignature();
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			final Method method = ms.getMethod();
			final String[] parameterNames = ms.getParameterNames();
			final Class<?>[] parameterTypes = ms.getParameterTypes();
			final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			final Object[] args = jp.getArgs();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				final Annotation[] annotations = parameterAnnotations[i];
				final T paramAnnotation = getAnnotationByType(annotations, getAnnotationClass());
				if (paramAnnotation != null) {
					this.processParameter(paramAnnotation, parameterTypes[i], args[i], parameterNames[i]);
				}
			}
		}
	}

	protected abstract void processParameter(final T paramAnnotation, final Class<?> paramType, Object parameter,
			final String paramName);

	protected abstract Class<T> getAnnotationClass();

	@SuppressWarnings("unchecked")
	private static <T extends Annotation> T getAnnotationByType(final Annotation[] annotations, final Class<T> clazz) {
		T result = null;
		for (final Annotation annotation : annotations) {
			if (clazz.isAssignableFrom(annotation.getClass())) {
				result = (T) annotation;
				break;
			}
		}
		return result;
	}

}
