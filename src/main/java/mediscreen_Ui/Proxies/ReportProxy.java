package mediscreen_Ui.Proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "msreport",url="http://localhost:8704")
public interface ReportProxy {
	 @RequestMapping("/report/patient/{id}")
	    String getReportByPatient(@PathVariable int id);

}
