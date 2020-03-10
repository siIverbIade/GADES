package com.cpd;

import java.util.List;

import com.cpd.entity.nodes.Localidade;
import com.cpd.repository.LocalidadeRepository;
import com.cpd.utils.Debug;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Gades.class)
public class ServiceApplicationTests {

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Test
    public void contextLoads() {
        List<Localidade> localidades = localidadeRepository.findByUfId((long)14711);
        Debug.Print(localidades);
    }
}