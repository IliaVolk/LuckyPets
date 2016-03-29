<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div id="container">
  <div id="top">
    <t:insertAttribute name="header" /> <!--<co id="co_tile_top" />-->
  </div>
  <div id="side">
    <t:insertAttribute name="signin_signout_side" /> <!--<co id="co_tile_side" />-->
  </div>
  <div id="content">
    <t:insertAttribute name="content" /> <!--<co id="co_tile_content" />-->
  </div>
  <div id="footer">
    <t:insertAttribute name="footer" />
  </div>
</div>
</body>
</html>
