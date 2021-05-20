package com.example.producingwebservice;

import javax.servlet.annotation.WebServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	// --- Put this URI to payments.xsd !!!
	static final String DEMO_URI = "https://www.thepay.cz/sender-demo-gate/api/payments-sent-api-demo.wsdl";
	static final String URI = "https://www.thepay.cz/sender-demo-gate/api/payments-sent-api.wsdl";

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet2(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	@Bean
	public MessageDispatcherServlet messageDispatcherServlet(ApplicationContext applicationContext) {

		return new ThePayMessageDispatcherServlet(applicationContext);
	}

	@Bean(name = "payments")
	public Wsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("PaymentsPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(DEMO_URI);
		wsdl11Definition.setSchema(countriesSchema);

		return wsdl11Definition;
	}

	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("payments.xsd"));
	}
}
