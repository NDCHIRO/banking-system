package BeansUtility;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String loginURI = httpRequest.getContextPath() + "/signIn.xhtml";
        String signUpURI = httpRequest.getContextPath() + "/index.xhtml";

        boolean loggedIn = (httpRequest.getSession().getAttribute("client") != null);
        boolean loginRequest = requestURI.equals(loginURI);
        boolean signUpRequest = requestURI.equals(signUpURI);


        if (loggedIn || loginRequest || signUpRequest) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(loginURI);
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
