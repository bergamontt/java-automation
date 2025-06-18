package compile;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes("compile.ComparatorProperty")
public class ComparatorProcessor extends AbstractProcessor {
    @Override
    public boolean process(
            Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv
    ) {

        for(Element element : roundEnv.getElementsAnnotatedWith(ComparatorProperty.class)) {
            if(element.getKind() != ElementKind.METHOD)
                continue;

            ExecutableElement executableElement = (ExecutableElement) element;
            TypeElement typeElement = (TypeElement) executableElement.getEnclosingElement();

            TypeMirror returnType = executableElement.getReturnType();
            if (!isComparable(returnType))
                continue;

            String className = typeElement.getSimpleName().toString();
            String methodName = executableElement.getSimpleName().toString();
            String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
            String comparatorName = className + "ComparatorBy" +
                    methodName.substring(0, 1).toUpperCase() +
                    methodName.substring(1);

           try {
               generateComparatorClass(packageName, className, methodName, comparatorName, returnType);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

        }

        return true;
    }

    private boolean isComparable(TypeMirror type) {
        if (type.getKind().isPrimitive())
            return true;
        Element element = processingEnv.getTypeUtils().asElement(type);
        TypeElement typeElement = (TypeElement) element;
        return processingEnv.getTypeUtils().isAssignable(typeElement.asType(), processingEnv
                                .getElementUtils()
                                .getTypeElement("java.lang.Comparable")
                                .asType());
    }

    private void generateComparatorClass(
            String packageName, String className,
            String methodName, String comparatorName, TypeMirror returnType
    ) throws IOException
    {
        TypeSpec.Builder comparatorBuilder = TypeSpec.classBuilder(comparatorName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(
                        ClassName.get("java.util", "Comparator"),
                        ClassName.get(packageName.isEmpty() ? "" : packageName, className)
                ));

        MethodSpec.Builder compareBuilder = MethodSpec.methodBuilder("compare")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .returns(int.class)
                .addParameter(ClassName.get(packageName.isEmpty() ? "" : packageName, className), "o1")
                .addParameter(ClassName.get(packageName.isEmpty() ? "" : packageName, className), "o2");

        if (returnType.getKind().isPrimitive()) {
            compareBuilder.addStatement("return $L", getComparisonCodeForPrimitive(returnType, methodName));
        } else {
            compareBuilder.addStatement("return (($T) o1.$L()).compareTo(o2.$L())",
                    TypeName.get(returnType), methodName, methodName);
        }

        comparatorBuilder.addMethod(compareBuilder.build());
        JavaFile javaFile = JavaFile.builder(packageName, comparatorBuilder.build())
                .build();
        javaFile.writeTo(processingEnv.getFiler());
    }

    private String getComparisonCodeForPrimitive(TypeMirror returnType, String methodName) {
        String expr = "o1." + methodName + "() - o2." + methodName + "()";
        return switch (returnType.getKind()) {
            case LONG -> "Long.compare(o1." + methodName + "(), o2." + methodName + "())";
            case FLOAT -> "Float.compare(o1." + methodName + "(), o2." + methodName + "())";
            case DOUBLE -> "Double.compare(o1." + methodName + "(), o2." + methodName + "())";
            default -> expr;
        };
    }

}
