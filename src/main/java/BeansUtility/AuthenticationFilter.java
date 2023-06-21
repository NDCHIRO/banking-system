package BeansUtility;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ORMs.Client;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String signinURI = httpRequest.getContextPath() + "/signIn.xhtml";
        String signUpURI = httpRequest.getContextPath() + "/index.xhtml";
        String accountURI = httpRequest.getContextPath() + "/account.xhtml";
        String moneyTransferURI = httpRequest.getContextPath() + "/moneyTransfer.xhtml";
        String transactionURI = httpRequest.getContextPath() + "/transaction.xhtml";
        
        boolean isClient = false;
        boolean loggedIn = false;
        if(httpRequest.getSession().getAttribute("client") != null)
         {
    		Client client = (Client)httpRequest.getSession().getAttribute("client");
    		loggedIn = (httpRequest.getSession().getAttribute("client") != null);
    		if (client.getRole().equals("client")) {
                loggedIn = true;
                isClient = true;
            }
          }

        boolean signinRequest = requestURI.equals(signinURI);
        boolean signUpRequest = requestURI.equals(signUpURI);
        boolean clientPageRequest = requestURI.equals(accountURI);
        boolean moneyTransferPageRequest = requestURI.equals(moneyTransferURI);
        boolean employeePageRequest = requestURI.equals(transactionURI);
        
        
        if (loggedIn) {
            if (isClient && (clientPageRequest || signUpRequest || signinRequest || moneyTransferPageRequest)) {
                chain.doFilter(request, response);
            } else if (!isClient && (employeePageRequest || signUpRequest || signinRequest)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(signinURI);
            }
        } else if (signinRequest || signUpRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(signinURI);
        }
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
    
    // Other filter methods (init, destroy) if needed

}
