package br.com.itau.acme.itauacme.models.mapper;


import br.com.itau.acme.itauacme.models.dto.QuoteDTO;
import br.com.itau.acme.itauacme.models.entities.QuoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface QuoteMapper {

    @Mapping(target = "id", ignore = true)
    QuoteEntity toMap(QuoteDTO quoteDTO);

    QuoteDTO toMap(QuoteEntity quoteEntity);
}
