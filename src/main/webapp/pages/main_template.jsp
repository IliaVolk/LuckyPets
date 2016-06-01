<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/> ">
  <link href="<c:url value="/resources/css/jumbotron-narrow.css" />" rel="stylesheet">
  <script type="text/javascript" src="<c:url value="/resources/js/clinic_map.js"/>"></script>
  <script src="http://maps.googleapis.com/maps/api/js"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/angular.min.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/clinics.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/articles.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/adverts.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/user_actions.js"/> "></script>


</head>
<body>
<div id="container">
  <div id="top" class="text-center">
    <t:insertAttribute name="header" /> <!--<co id="co_tile_top" />-->
  </div>
  <ul class="nav nav-pills">
    <s:url value="articles" var="articlesUrl"/>
    <li><a href="${articlesUrl}"><s:message code="Articles"/> </a></li>
  </ul>
  <div class="row">
    <div class="col-sm-8">
      <t:insertAttribute name="content"/> <!--<co id="co_tile_content" />-->
    </div>
    <div class="col-sm-4">
      <t:insertAttribute name="signin_signout_side"/> <!--<co id="co_tile_side" />-->
    </div>
  </div>
  <div id="footer" class="text-center">
    <t:insertAttribute name="footer" />
  </div>
</div>
</body>
</html>
