<html>
<head>
    <title>Login Page</title>
    <script type="text/javascript">

        function checkFormFactor() {
            var args = location.search;
            var start = args.indexOf("formfactor");
            if (start >= 0) {
                var value = args.substring(start);
                var begin = value.indexOf("=") + 1;
                var end = value.indexOf("&");
                if (end == -1) {
                    end = value.length;
                }
                return value.substring(begin, end);
            }

            // Detect form factor from user agent.
            var ua = navigator.userAgent.toLowerCase();
            if (ua.indexOf("iphone") != -1 || ua.indexOf("ipod") != -1) {
                // iphone and ipod.
                return "mobile";
            } else if (ua.indexOf("ipad") != -1) {
                // ipad.
                return "tablet";
            } else if (ua.indexOf("android") != -1 || ua.indexOf("mobile") != -1) {
                /*
                 * Android - determine the form factor of android devices based on the diagonal screen
                 * size. Anything under six inches is a phone, anything over six inches is a tablet.
                 */
                var dpi = 160;
                var width = $wnd.screen.width / dpi;
                var height = $wnd.screen.height / dpi;
                var size = Math.sqrt(width * width + height * height);
                return (size < 6) ? "mobile" : "tablet";
            }

            // Everything else is a desktop.
            return "desktop";
        }
    </script>
</head>
<body>
<table align="center" border="0" cellspacing="0" cellpadding="0" width="300px">
    <tbody>
    <tr>
        <td>
            <table class="header" cellspacing="0" cellpadding="0" border="0" width="100%">
                <tbody>
                <tr>
                    <td align="center" width="100%"><img border="0" alt="Company's LOGO"
                                                         src="<%=request.getContextPath()%>\images\logo.png">
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
        <td valign="top">
            <form action='j_security_check' id="loginForm" method="post" class="loginbox" autocomplete="on">
                <table align="center" border="0" cellspacing="5" cellpadding="10" width="100%">
                    <tbody>
                    <tr valign="center">
                        <td colspan="2" align="center"><h3>Enter User Name and Password</h3></td>
                    </tr>

                    <tr valign="top">
                        <td width="50%" align="right"><label id="login">Login</label></td>
                        <td width="50%" align="left">
                            <input name="j_username" type="text" id="j_username"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" align="right"><label id="password">Password</label></td>
                        <td width="50%" align="left">
                            <input name="j_password" type="password" id="j_password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" width="100%" align="center">
                            <input type="submit" id="submit" value="Log In"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" width="100%" align="center"><h2><font color="red"></font></h2></td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="top" align="center">
                            <h2></h2></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </td>
    </tr>

    <tr>
        <td align="center" colspan="2" height="150"></td>
    </tr>
    <tr>
        <td align="center">
            <table width="300px" cellspacing="0" cellpadding="0" border="0">
                <tbody>
                <tr class="footerColor" height="20">
                    <td align="center">2014 TSU, Vako Beridze</td>
                </tr>
                </tbody>
            </table>

        </td>
    </tr>
    </tbody>
</table>


</body>
</html>