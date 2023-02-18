package com.anderson.trakd;

import com.anderson.trakd.model.NHCompanyCredentials;
import com.anderson.trakd.repository.NHCompanyCredentialsRepository;
import com.anderson.trakd.service.NHCompanyCredentialsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class NHCompanyCredentialsServiceTest {


    private NHCompanyCredentialsService nhCompanyCredentialsService;

    private NHCompanyCredentialsRepository nhCompanyCredentialsRepository;

    @Test
    public void testCreateCompanyCredentials(){
        nhCompanyCredentialsRepository = mock(NHCompanyCredentialsRepository.class);
        nhCompanyCredentialsService = new NHCompanyCredentialsService(nhCompanyCredentialsRepository);
        NHCompanyCredentials nhCompanyCredentials = new NHCompanyCredentials(100L,"12345","email", "password");
        nhCompanyCredentialsService.createCompanyCredentials(nhCompanyCredentials);
        verify(nhCompanyCredentialsRepository, times(1)).save(nhCompanyCredentials);
    }

}
