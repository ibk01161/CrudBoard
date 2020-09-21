function checkImageType(fileName) {
	
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getFileInfo(fullName) {
	var fileName, imgsrc, getLink;
	var fileLink;
	
	if(checkImageType(fullName)) {
		imgsrc = "/crud/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12);	// /년/월/일/
		var end = fullName.substr(14);
		
		getLink = "/crud/displayFile?fileName="+front + end;
	} else {
		imgsrc = "../resources/dist/img/file.png";
		fileLink = fullName.substr(12);
		getLink = "/crud/displayFile?fileName="+fullName;
	}
	fileName = fileLink.substr(fileLink.indexOf("_") + 1);
	
	return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}