package com.rutagemelar.back.service;

 import com.fasterxml.jackson.core.type.TypeReference;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import com.rutagemelar.back.model.SemanaEmbarazo;
 import com.rutagemelar.back.repository.SemanaEmbarazoRepository;
 import jakarta.annotation.PostConstruct;
 import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Component;

 import java.io.InputStream;
 import java.util.List;


@Component
@RequiredArgsConstructor
public class SeederSemanaEmbarazo {

    private final SemanaEmbarazoRepository semanaRepo;

    @PostConstruct
    public void seedData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("embarazo_semanal.json");
            if (is == null) {
                System.err.println("No se encontro archivo json en resources");
                return;
            }
            List<SemanaEmbarazo> semanas= mapper.readValue(is, new TypeReference<>() {});
            semanaRepo.saveAll(semanas);


            System.out.println("Cargadas " + semanas.size() + " semanas de embarazo.");
        } catch (Exception e) {
            System.err.println("Error al crgar las semanas : " + e.getMessage());
        }

    }
}
