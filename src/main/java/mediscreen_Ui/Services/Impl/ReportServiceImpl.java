package mediscreen_Ui.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mediscreen_Ui.Proxies.ReportProxy;
import mediscreen_Ui.Services.ReportService;
@Service
public class ReportServiceImpl implements ReportService {
	 @Autowired
	    private ReportProxy reportProxy;
	@Override
	public String getReport(int patientId) {
		
		   return reportProxy.getReportByPatient(patientId);
	}

}
