function isChecked() {
	if (document.getElementById("checkbox").checked) {
		location.href = '/signup';
	} else {
		alert('規約に同意しなければ続けられません。');
		return;
	}
}

function loginValidate() {
	if (document.getElementById("id").value == "") {
		alert('IDを入力してください。')
		document.getElementById("id").focus();
		return false;
	}
	if (document.getElementById("pwd").value == "") {
		alert('パスワードを入力してください。');
		document.getElementById("pwd").focus();
		return false;
	}
	return true;
}

function confirmpwd() {
	var isTrue;
	if (document.getElementById("pwd").value.length < 8 || document.getElementById("pwd").value.length > 20) {
		alert('パスワードは8文字以上２０文字以下でなければなりません。')
		document.getElementById("pwd").focus();
		isTrue = false;
		return !!(isTrue);
	}
	if (document.getElementById("repwd").value != document.getElementById("pwd").value) {
		alert('パスワードが一致していません。')
		document.getElementById("repwd").focus();
		isTrue = false;
		return !!(isTrue);
	} else {
		alert('パスワードが一致しています。');
		document.getElementById("pcon").value = "完了";
		document.getElementById("pcon").disabled = true;
		document.getElementById("repwd").disabled = true;
		document.getElementById("pwd").readOnly = true;
		document.getElementById("hp").focus();
		isTrue = true;
		return !!(isTrue);
	}
}
function confirmsignup() {
	//공란확인
	if (document.getElementById("id").value == "") {
		alert('IDを入力してください。')
		document.getElementById("id").focus();
		return false;
	}
	if (document.getElementById("id").value.length < 5 || document.getElementById("id").value.length > 12) {
		alert('IDは５文字以上１２文字以下でなければなりません。')
		document.getElementById("id").focus();
		return false;
	}
	if (document.getElementById("pwd").value == "") {
		alert('パスワードを入力してください。')
		document.getElementById("pwd").focus();
		return false;
	}
	if (document.getElementById("hp").value == "") {
		alert('携帯電話を入力してください。')
		document.getElementById("hp").focus();
		return false;
	}
	if (!(pcon)) {
		alert('パスワード検査を行ってください。')
		document.getElementById("repwd").focus();
		return false;
	}
	return true;
}

function selkind(kind) {
	document.getElementById("searchbtn").innerText = kind;
}

function search() {
	kind = document.getElementById("searchbtn").innerText;
	svalue = document.getElementById("search").value;
	location.href = "searchList.jsp?kind=" + kind + "&search=" + svalue;
}

function xmlToJson(xml) {
	  var obj = {};

	  if (xml.nodeType == 1) {
	    if (xml.attributes.length > 0) {
	      obj["@attributes"] = {};
	      for (var j = 0; j < xml.attributes.length; j++) {
	        var attribute = xml.attributes.item(j);
	        obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
	      }
	    }
	  } else if (xml.nodeType == 3) {
	    obj = xml.nodeValue;
	  }

	  var textNodes = [].slice.call(xml.childNodes).filter(function(node) {
	    return node.nodeType === 3;
	  });
	  if (xml.hasChildNodes() && xml.childNodes.length === textNodes.length) {
	    obj = [].slice.call(xml.childNodes).reduce(function(text, node) {
	      return text + node.nodeValue;
	    }, "");
	  } else if (xml.hasChildNodes()) {
	    for (var i = 0; i < xml.childNodes.length; i++) {
	      var item = xml.childNodes.item(i);
	      var nodeName = item.nodeName;
	      if (typeof obj[nodeName] == "undefined") {
	        obj[nodeName] = xmlToJson(item);
	      } else {
	        if (typeof obj[nodeName].push == "undefined") {
	          var old = obj[nodeName];
	          obj[nodeName] = [];
	          obj[nodeName].push(old);
	        }
	        obj[nodeName].push(xmlToJson(item));
	      }
	    }
	  }
	  return obj;
	}
	
function parseXML (data) {
    var xml, tmp;
    if (!data || typeof data !== "string") {
        return null;
    }
    try {
        if (window.DOMParser) {
            tmp = new DOMParser();
            xml = tmp.parseFromString(data, "text/xml");
        } else {
            xml = new ActiveXObject("Microsoft.XMLDOM");
            xml.async = "false";
            xml.loadXML(data);
        }
    } catch(e) {
        xml = undefined;
    }
    if (!xml || !xml.documentElement || xml.getElementsByTagName("parsererror").length) {
        throw new Error("Invalid XML: " + data);
    }
    return xml;
}

function detail(i) {
	var isHidden = document.getElementById("D"+i).hidden;
	document.getElementById("D"+i).hidden = (isHidden) ? false:true;
}