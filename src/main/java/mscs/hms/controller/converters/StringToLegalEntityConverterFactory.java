package mscs.hms.controller.converters;

import mscs.hms.model.Company;
import mscs.hms.model.LegalEntity;
import mscs.hms.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class StringToLegalEntityConverterFactory
        implements ConverterFactory<String, LegalEntity> {

    @Override
    public <T extends LegalEntity> Converter<String, T> getConverter(Class<T> targetClass) {
        return new StringToLegalEntityConverter<>(targetClass);
    }

    private static class StringToLegalEntityConverter<T extends LegalEntity>
            implements Converter<String, T> {

        private Class<T> targetClass;

        public StringToLegalEntityConverter(Class<T> targetClass) {
            this.targetClass = targetClass;
        }

        @Override
        public T convert(String source) {
            long id = Long.parseLong(source);
            if(this.targetClass == Company.class) {
                return (T) new Company();
            }
            else if(this.targetClass == Person.class) {
                return (T) new Person();
            } else {
                return null;
            }
        }
    }
}