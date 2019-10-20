package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        System.out.println("req/resp UTF-8");
        chain.doFilter(req, resp);
    }

}
