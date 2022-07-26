package mediscreen_Ui.Services.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mediscreen_Ui.Proxies.ReportProxy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

	 @Mock
	    private ReportProxy mockReportProxy;

	    @InjectMocks
	    private ReportServiceImpl reportServiceProxyImplUnderTest;

	    @Test
	    void testGetReport() {
	        when(mockReportProxy.getReportByPatient(0)).thenReturn("result");

	        final String result = reportServiceProxyImplUnderTest.getReport(0);

	        assertThat(result).isEqualTo("result");
	    }

}
