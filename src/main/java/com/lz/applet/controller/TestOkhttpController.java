package com.lz.applet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lz.applet.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.stream.Collectors;

@RestController
@Api(tags = "测试OKHTTP")
public class TestOkhttpController {

    final String testString = "{\"data\":{\"name\":\"file\",\"originalFilename\":\"1111.xls\",\"contentType\":\"text/plain\",\"bytes\":\"0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAPgADAP7/CQAGAAAAAAAAAAAAAAABAAAAAQAAAAAAAAAAEAAALAAAAAEAAAD+////AAAAAAAAAAD////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////9////LwAAAAMAAAAEAAAABQAAAAYAAAAHAAAACAAAAAkAAAAKAAAACwAAAAwAAAANAAAADgAAAA8AAAAQAAAAEQAAABIAAAATAAAAFAAAABUAAAAWAAAAFwAAABgAAAAZAAAAGgAAABsAAAAcAAAAHQAAAB4AAAAfAAAAIAAAACEAAAAiAAAAIwAAACQAAAAlAAAAJgAAACcAAAAoAAAAKQAAACoAAAArAAAA/v////7///8uAAAAMAAAAP7////+/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////1IAbwBvAHQAIABFAG4AdAByAHkAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAWAAUA//////////8CAAAAIAgCAAAAAADAAAAAAAAARgAAAAAAAAAAAAAAAOAsu4aU8NkBLQAAAMAFAAAAAAAAVwBvAHIAawBiAG8AbwBrAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABIAAgH///////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAQ1MAAAAAAABFAFQARQB4AHQARABhAHQAYQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFAACAQEAAAADAAAA/////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACqAgAAAAAAAAUAUwB1AG0AbQBhAHIAeQBJAG4AZgBvAHIAbQBhAHQAaQBvAG4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAoAAIB/////wQAAAD/////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACwAAABQBAAAAAAAACQgQAAAGBQC7Dc0HwYABAAYGAADhAAIAsATBAAIAAADiAAAAXABwAAYAAGxlbm9ub2EAbgAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBCAAIAsARhAQIAAADAAQAAPQEGAAEAAgADABkAAgAAABIAAgAAABMAAgAAAK8BAgAAALwBAgAAAD0AEgAAAAAA3W38MDgAAAAAAAEAWAJAAAIAAACNAAIAAAAiAAIAAAAOAAIAAQC3AQIAAADaAAIAAAAxABQA8AAAAP9/kAEAAAAAhgACAYtbU08xABQA3AAAAP9/kAEAAAAAhgACAYtbU08xABQA3AAAAP9/kAEAAAAAhgACAYtbU08xABQA3AAAAP9/kAEAAAAAhgACAYtbU08xABQAQAEAAP9/vAIAAAADhgACAdGeU08xACIAGAEAAP9/vAIAAAAAhgAJAf9Oi1tfAEcAQgAyADMAMQAyADEAGAAYAQAA/3+8AgAAAACGAAQBTlOHZS1Oi1sxABQAyAAAAP9/kAEAAAAAhgACAYtbU08xABQAyAAAAP9/kAEAAAAAhgACAYtbU08xABQAoAAAAP9/kAEAAAAAhgACAYtbU08xABQA3AAAAAgAkAEAAAAAhgACAYtbU08xABQA3AAAAD4AkAEAAAAAhgACAYtbU08xABQA3AAAABAAkAEAAAAAhgACAYtbU08xABQA3AAAAAkAkAEAAAAAhgACAYtbU08xABQA3AAAAAwAkAEAAAEAhgACAYtbU08xABQA3AAAABQAkAEAAAEAhgACAYtbU08xABQA3AAAAD4AvAIAAAAAhgACAYtbU08xABQA3AAAAAoAkAEAAAAAhgACAYtbU08xABQAaAEAAD4AvAIAAAAAhgACAYtbU08xABQA3AACABcAkAEAAAAAhgACAYtbU08xABQALAEAAD4AvAIAAAAAhgACAYtbU08xABQABAEAAD4AvAIAAAAAhgACAYtbU08xABQA3AAAAD8AvAIAAAAAhgACAYtbU08xABQA3AAAADUAvAIAAAAAhgACAYtbU08xABQA3AAAAAkAvAIAAAAAhgACAYtbU08xABQA3AAAADUAkAEAAAAAhgACAYtbU08xABQA3AAAAAgAvAIAAAAAhgACAYtbU08xABQA3AAAABEAkAEAAAAAhgACAYtbU08xABQA3AAAABMAkAEAAAAAhgACAYtbU08eBCsABQATAAEiAOX/IgAjACwAIwAjADAAOwAiAOX/IgBcAC0AIwAsACMAIwAwAB4ENQAGABgAASIA5f8iACMALAAjACMAMAA7AFsAUgBlAGQAXQAiAOX/IgBcAC0AIwAsACMAIwAwAB4ENwAHABkAASIA5f8iACMALAAjACMAMAAuADAAMAA7ACIA5f8iAFwALQAjACwAIwAjADAALgAwADAAHgRBAAgAHgABIgDl/yIAIwAsACMAIwAwAC4AMAAwADsAWwBSAGUAZABdACIA5f8iAFwALQAjACwAIwAjADAALgAwADAAHgRpACoAMgABXwAgACIA5f8iACoAIAAjACwAIwAjADAAXwAgADsAXwAgACIA5f8iACoAIABcAC0AIwAsACMAIwAwAF8AIAA7AF8AIAAiAOX/IgAqACAAIgAtACIAXwAgADsAXwAgAEAAXwAgAB4EVwApACkAAV8AIAAqACAAIwAsACMAIwAwAF8AIAA7AF8AIAAqACAAXAAtACMALAAjACMAMABfACAAOwBfACAAKgAgACIALQAiAF8AIAA7AF8AIABAAF8AIAAeBHkALAA6AAFfACAAIgDl/yIAKgAgACMALAAjACMAMAAuADAAMABfACAAOwBfACAAIgDl/yIAKgAgAFwALQAjACwAIwAjADAALgAwADAAXwAgADsAXwAgACIA5f8iACoAIAAiAC0AIgA/AD8AXwAgADsAXwAgAEAAXwAgAB4EZwArADEAAV8AIAAqACAAIwAsACMAIwAwAC4AMAAwAF8AIAA7AF8AIAAqACAAXAAtACMALAAjACMAMAAuADAAMABfACAAOwBfACAAKgAgACIALQAiAD8APwBfACAAOwBfACAAQABfACAAHgQvABcAFQABXAAkACMALAAjACMAMABfACkAOwBcACgAXAAkACMALAAjACMAMABcACkAHgQ5ABgAGgABXAAkACMALAAjACMAMABfACkAOwBbAFIAZQBkAF0AXAAoAFwAJAAjACwAIwAjADAAXAApAB4EOwAZABsAAVwAJAAjACwAIwAjADAALgAwADAAXwApADsAXAAoAFwAJAAjACwAIwAjADAALgAwADAAXAApAB4ERQAaACAAAVwAJAAjACwAIwAjADAALgAwADAAXwApADsAWwBSAGUAZABdAFwAKABcACQAIwAsACMAIwAwAC4AMAAwAFwAKQDgABQAAAAAAPX/EAAAAAAAAAAAAAAAwCDgABQAAQAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAQAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAgAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAgAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAAAAAAAEAEAAAAAAAAAAAAAAAwCDgABQAAAAqAPX/EAAA+AAAAAAAAAAAwCDgABQACwAAAPX/EAAAtAAAAAAAAAAEGiDgABQADAAAAPX/EAAAlBERlwuXCwAELyDgABQAAAAsAPX/EAAA+AAAAAAAAAAAwCDgABQAAAApAPX/EAAA+AAAAAAAAAAAwCDgABQACwAAAPX/EAAAtAAAAAAAAAAEKiDgABQADQAAAPX/EAAAtAAAAAAAAAAELSDgABQAAAArAPX/EAAA+AAAAAAAAAAAwCDgABQADgAAAPX/EAAAtAAAAAAAAAAEFiDgABQADwAAAPX/EAAA9AAAQCBAIAAAwCDgABQAAAAJAPX/EAAA+AAAAAAAAAAAwCDgABQAEAAAAPX/EAAA9AAAQCBAIAAAwCDgABQAAAAAAPX/EAAAnBERFgsWCwAEGiDgABQADgAAAPX/EAAAtAAAAAAAAAAEHSDgABQAEQAAAPX/EAAA9AAAAAAAAAAAwCDgABQAEgAAAPX/EAAA9AAAAAAAAAAAwCDgABQAEwAAAPX/EAAA9AAAAAAAAAAAwCDgABQAFAAAAPX/EAAA9AAAAAAAAAAAwCDgABQAFQAAAPX/EAAA1ABQAAAAGwAAwCDgABQAFgAAAPX/EAAA1ABQAAAAFgAAwCDgABQADgAAAPX/EAAAtAAAAAAAAAAEFiDgABQAEQAAAPX/EAAA1AAgAAAACwAAwCDgABQADgAAAPX/EAAAtAAAAAAAAAAEFiDgABQAFwAAAPX/EAAAlBERvx+/HwAECSDgABQAGAAAAPX/EAAAlBERlwuXCwAECSDgABQAGQAAAPX/EAAAlGZmvx+/HwAENyDgABQACwAAAPX/EAAAtAAAAAAAAAAEGiDgABQADgAAAPX/EAAAtAAAAAAAAAAEGSDgABQAGgAAAPX/EAAA1ABg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AAAAAAAAAAgAFAADAAAABAAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAAC4AAAADAAQAFAACAAAAxu/O/wAAAAAAAAAADQAUAAIAAAAAYQD/AAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAAC8AAAADAAQAFAACAAAA/+uc/wAAAAAAAAAADQAUAAIAAACcZQD/AAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADAAAAADAAQAFAADAGVmCAAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADEAAAADAAQAFAADAAAABAAAAAAAAAAAAAAADQAUAAMAAAAAAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADIAAAADAAQAFAADAGVmBAAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADMAAAADAAQAFAADAMxMBAAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADQAAAADAAQAFAADAGVmBQAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADUAAAADAAQAFAADAMxMBQAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADYAAAADAAQAFAADAAAABgAAAAAAAAAAAAAADQAUAAMAAAAAAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADcAAAADAAQAFAADAAAABwAAAAAAAAAAAAAADQAUAAMAAAAAAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADgAAAADAAQAFAADAGVmBwAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADkAAAADAAQAFAADAMxMBwAAAAAAAAAAAAAADQAUAAMAAAABAAAAAAAAAAAAAAAOAAUAAn0IQQB9CAAAAAAAAAAAAAAAADoA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CgABNAAyADIAMAA3ADAANQAwADEANQACAAGXZxxaCgABNAAyADIAMAA3ADAANQAwADEANgADAAFVT1Nm5mYDAAE0VGNrxJYKAAE0ADIAMgAwADcAMAA1ADAAMQA3AAIAAVd/3pgDAAFuZvpem1EKAAE0ADIAMgAwADcAMAA1ADAAMQA4AAIAAU+b9H4DAAFOZ+SAHE4KAAE0ADIAMgAwADcAMAA1ADAAMQA5AAMAAdGQh2WMZQMAAd2Q4HqJjwoAATQAMgAyADAANwAwADUAMAAyADAAAwAB4YDKT8t5CgABNAAyADIAMAA3ADAANQAwADIAMQADAAHEnlNm14sKAAE0ADIAMgAwADcAMAA1ADAAMgAyAAMAAU5n404fTwMAAU5nWG7HUgoAATQAMgAyADAANwAwADUAMAAyADMAAwABVE4RXHBnAwABEFW3X06GCgABNAAyADIAMAA3ADAANQAwADIANAACAAGLc+5xAgABloDulAoAATQAMgAyADAANwAwADUAMAAyADUAAwABiVvKT8B5AgABi3P0fgoAATQAMgAyADAANwAwADUAMAAyADYAAwAB0ZCOdsxRAwABi3PtZQttCgABNAAyADIAMAA3ADAANQAwADIANwADAAG4i5Buc14JAAHqj5tSRnrJYtBjtwAQVBRcCpAKAAE0ADIAMgAwADcAMAA1ADAAMgA4ADkAAWtYaIi6Thr/IAAgACAAIAAgACAAIAAgADtOoXtiln+Vfnvgehr/IAAgACAAIAAgACAAIAAgACAAIAAgACAAIAAgACAAIAAgACAAIAAgAGtYaIjlZR9nGv8gACAAIAAgAHReIAAgACAACGcgACAAIADlZf8ACgBdACk2AAAMAAAACgAAAAkIEAAABhAAuw3NB8GAAQAGBgAACwIYAAAAAAAAAAAAJAAAACUHAAALTwAAo08AAA0AAgABAAwAAgBkAA8AAgABABEAAgAAABAACAD8qfHSTWJQP18AAgABACoAAgAAACsAAgAAAIIAAgABAIAACAAAAAAAAAAAACUCBAAAAB0BgQACAMEEFAAAABUAHQANAAEseyAAJgBQACAAdZgM/3FRIAAmAE4AIAB1mIMAAgABAIQAAgAAACYACAAKhUKhUCjUPycACAAKhUKhUCjUPygACACs1Wq1Wq3WPykACACQx+PxeDzeP6EAIgAJAGQAAQABAAEAAABYAlgCCoVCoVAoxD9NJpPJZDLJPwEAnAgmAJwIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAVQACAAgAfQAMAAAAAADgAw8AAgAEAH0ADAABAAEAwAgPAAIABAB9AAwAAgACAGAJDwACAAQAfQAMAAMAAwDgBA8AAgAEAH0ADAAEAAQAQAsPAAIABAB9AAwABQAFAKAQDwACAAQAfQAMAAYABgCgDQ8AAgAEAH0ADAAHAAcAwAgPAAIABAB9AAwACAAIAGAKDwACAAQAAAIOAAAAAAAkAAAAAAAKAAAACAIQAAAAAAAKAEkCAAAAAEABDwAIAhAAAQAAAAoASQIAAAAAQAEPAAgCEAACAAAACgDgAQAAAACAAUAACAIQAAMAAAAKAB0BAAAAAIABQAAIAhAABAAAAAoAHQEAAAAAgAFAAAgCEAAFAAAACgAdAQAAAACAAUAACAIQAAYAAAAKAB0BAAAAAIABQAAIAhAABwAAAAoAHQEAAAAAgAFAAAgCEAAIAAAACgAdAQAAAACAAUAACAIQAAkAAAAKAB0BAAAAAIABQAAIAhAACgAAAAoAHQEAAAAAgAFAAAgCEAALAAAACgAdAQAAAACAAUAACAIQAAwAAAAKAB0BAAAAAIABQAAIAhAADQAAAAoAHQEAAAAAgAFAAAgCEAAOAAAACgAdAQAAAACAAUAACAIQAA8AAAAKAB0BAAAAAIABQAAIAhAAEAAAAAoAHQEAAAAAgAFAAAgCEAARAAAACgAdAQAAAACAAUAACAIQABIAAAAKAB0BAAAAAIABQAAIAhAAEwAAAAoAHQEAAAAAgAFAAAgCEAAUAAAACgAdAQAAAACAAUAACAIQABUAAAAKAB0BAAAAAIABQAAIAhAAFgAAAAoAHQEAAAAAgAFAAAgCEAAXAAAACgAdAQAAAACAAUAACAIQABgAAAAKAB0BAAAAAIABQAAIAhAAGQAAAAoAHQEAAAAAgAFAAAgCEAAaAAAACgAdAQAAAACAAUAACAIQABsAAAAKAB0BAAAAAIABQAAIAhAAHAAAAAoAHQEAAAAAgAFAAAgCEAAdAAAACgAdAQAAAACAAUAACAIQAB4AAAAKAOABAAAAAIABQAAIAhAAHwAAAAoAKQQAAAAAQAEPAP0ACgAAAAAAQQAAAAAAvgAWAAAAAQBBAEEAQQBBAEEAQQBBAEEACAD9AAoAAQAAAEIAAQAAAL4AFgABAAEAQgBCAEIAQwBDAEMARABEAAgA/QAKAAIAAABFAAIAAAD9AAoAAgABAEUAAwAAAP0ACgACAAIARgAEAAAA/QAKAAIAAwBGAAUAAAD9AAoAAgAEAEYABgAAAP0ACgACAAUARgAHAAAA/QAKAAIABgBFAAgAAAD9AAoAAgAHAEUACQAAAP0ACgACAAgARQAKAAAA/QAKAAIACQBJAAsAAAB+AgoAAwAAAEUAAADwP/0ACgADAAEARwAMAAAA/QAKAAMAAgBHAA0AAAB+AgoAAwADAEYAAJifQP0ACgADAAQARgAOAAAA/QAKAAMABQBGAA8AAAB+AgoAAwAGAEUAAAAoQL4ACgADAAcARQBFAAgA/QAKAAMACQBKABAAAAB+AgoABAAAAEUAAAAAQP0ACgAEAAEARwARAAAA/QAKAAQAAgBHABIAAAB+AgoABAADAEYAAJifQP0ACgAEAAQARgAOAAAA/QAKAAQABQBGAA8AAAB+AgoABAAGAEUAAAAoQL4ACgAEAAcARQBFAAgA/QAKAAQACQBKABMAAAB+AgoABQAAAEUAAAAIQP0ACgAFAAEARwAUAAAA/QAKAAUAAgBHABUAAAB+AgoABQADAEYAAJifQP0ACgAFAAQARgAOAAAA/QAKAAUABQBGAA8AAAB+AgoABQAGAEUAAAAoQL4ACgAFAAcARQBFAAgA/QAKAAUACQBKABYAAAB+AgoABgAAAEUAAAAQQP0ACgAGAAEARwAXAAAA/QAKAAYAAgBHABgAAAB+AgoABgADAEYAAJifQP0ACgAGAAQARgAOAAAA/QAKAAYABQBGAA8AAAB+AgoABgAGAEUAAAAoQL4ACgAGAAcARQBFAAgA/QAKAAYACQBKABkAAAB+AgoABwAAAEUAAAAUQP0ACgAHAAEARwAaAAAA/QAKAAcAAgBHABsAAAB+AgoABwADAEYAAJifQP0ACgAHAAQARgAOAAAA/QAKAAcABQBGAA8AAAB+AgoABwAGAEUAAAAoQL4ACgAHAAcARQBFAAgA/QAKAAcACQBKABwAAAB+AgoACAAAAEUAAAAYQP0ACgAIAAEARwAdAAAA/QAKAAgAAgBHAB4AAAB+AgoACAADAEYAAJifQP0ACgAIAAQARgAOAAAA/QAKAAgABQBGAA8AAAB+AgoACAAGAEUAAAAoQL4ACgAIAAcARQBFAAgA/QAKAAgACQBKABwAAAB+AgoACQAAAEUAAAAcQP0ACgAJAAEARwAfAAAA/QAKAAkAAgBHACAAAAB+AgoACQADAEYAAJifQP0ACgAJAAQARgAOAAAA/QAKAAkABQBGAA8AAAB+AgoACQAGAEUAAAAoQL4ACgAJAAcARQBFAAgA/QAKAAkACQBKACEAAAB+AgoACgAAAEUAAAAgQP0ACgAKAAEARwAiAAAA/QAKAAoAAgBHACMAAAB+AgoACgADAEYAAJifQP0ACgAKAAQARgAOAAAA/QAKAAoABQBGAA8AAAB+AgoACgAGAEUAAAAoQL4ACgAKAAcARQBFAAgA/QAKAAoACQBKACQAAAB+AgoACwAAAEUAAAAiQP0ACgALAAEARwAlAAAA/QAKAAsAAgBHACYAAAB+AgoACwADAEYAAJifQP0ACgALAAQARgAOAAAA/QAKAAsABQBGAA8AAAB+AgoACwAGAEUAAAAoQL4ACgALAAcARQBFAAgA/QAKAAsACQBKACcAAAB+AgoADAAAAEUAAAAkQP0ACgAMAAEARwAoAAAA/QAKAAwAAgBHACkAAAB+AgoADAADAEYAAJifQP0ACgAMAAQARgAOAAAA/QAKAAwABQBGAA8AAAB+AgoADAAGAEUAAAAoQL4ACgAMAAcARQBFAAgA/QAKAAwACQBKACoAAAB+AgoADQAAAEUAAAAmQP0ACgANAAEARwArAAAA/QAKAA0AAgBHACwAAAB+AgoADQADAEYAAJifQP0ACgANAAQARgAOAAAA/QAKAA0ABQBGAA8AAAB+AgoADQAGAEUAAAAoQL4ACgANAAcARQBFAAgA/QAKAA0ACQBKAC0AAAB+AgoADgAAAEUAAAAoQP0ACgAOAAEARwAuAAAA/QAKAA4AAgBHAC8AAAB+AgoADgADAEYAAJifQP0ACgAOAAQARgAOAAAA/QAKAA4ABQBGAA8AAAB+AgoADgAGAEUAAAAoQL4ACgAOAAcARQBFAAgA/QAKAA4ACQBKABMAAAB+AgoADwAAAEUAAAAqQP0ACgAPAAEARwAwAAAA/QAKAA8AAgBHADEAAAB+AgoADwADAEYAAJifQP0ACgAPAAQARgAOAAAA/QAKAA8ABQBGAA8AAAB+AgoADwAGAEUAAAAoQL4ACgAPAAcARQBFAAgA/QAKAA8ACQBKADIAAAB+AgoAEAAAAEUAAAAsQP0ACgAQAAEARwAzAAAA/QAKABAAAgBHADQAAAB+AgoAEAADAEYAAJifQP0ACgAQAAQARgAOAAAA/QAKABAABQBGAA8AAAB+AgoAEAAGAEUAAAAoQL4ACgAQAAcARQBFAAgA/QAKABAACQBKADUAAAB+AgoAEQAAAEUAAAAuQP0ACgARAAEARwA2AAAA/QAKABEAAgBHADcAAAB+AgoAEQADAEYAAJifQP0ACgARAAQARgAOAAAA/QAKABEABQBGAA8AAAB+AgoAEQAGAEUAAAAoQL4ACgARAAcARQBFAAgA/QAKABEACQBKADUAAAB+AgoAEgAAAEUAAAAwQP0ACgASAAEARwA4AAAA/QAKABIAAgBHADkAAAB+AgoAEgADAEYAAJifQP0ACgASAAQARgAOAAAA/QAKABIABQBGAA8AAAB+AgoAEgAGAEUAAAAoQL4ACgASAAcARQBFAAgA/QAKABIACQBKADoAAAB+AgoAEwAAAEUAAAAxQP0ACgATAAEARwA7AAAA/QAKABMAAgBHADwAAAB+AgoAEwADAEYAAJifQP0ACgATAAQARgAOAAAA/QAKABMABQBGAA8AAAB+AgoAEwAGAEUAAAAoQL4ACgATAAcARQBFAAgA/QAKABMACQBKAD0AAAB+AgoAFAAAAEUAAAAyQP0ACgAUAAEARwA+AAAA/QAKABQAAgBHAD8AAAB+AgoAFAADAEYAAJifQP0ACgAUAAQARgAOAAAA/QAKABQABQBGAA8AAAB+AgoAFAAGAEUAAAAoQL4ACgAUAAcARQBFAAgA/QAKABQACQBKAEAAAAB+AgoAFQAAAEUAAAAzQP0ACgAVAAEARwBBAAAA/QAKABUAAgBHAEIAAAB+AgoAFQADAEYAAJifQP0ACgAVAAQARgAOAAAA/QAKABUABQBGAA8AAAB+AgoAFQAGAEUAAAAoQL4ACgAVAAcARQBFAAgA/QAKABUACQBKAEMAAAB+AgoAFgAAAEUAAAA0QP0ACgAWAAEARwBEAAAA/QAKABYAAgBHAEUAAAB+AgoAFgADAEYAAJifQP0ACgAWAAQARgAOAAAA/QAKABYABQBGAA8AAAB+AgoAFgAGAEUAAAAoQL4ACgAWAAcARQBFAAgA/QAKABYACQBKABAAAAB+AgoAFwAAAEUAAAA1QP0ACgAXAAEARwBGAAAA/QAKABcAAgBHAEcAAAB+AgoAFwADAEYAAJifQP0ACgAXAAQARgAOAAAA/QAKABcABQBGAA8AAAB+AgoAFwAGAEUAAAAoQL4ACgAXAAcARQBFAAgA/QAKABcACQBKAC0AAAB+AgoAGAAAAEUAAAA2QP0ACgAYAAEARwBIAAAA/QAKABgAAgBHAEkAAAB+AgoAGAADAEYAAJifQP0ACgAYAAQARgAOAAAA/QAKABgABQBGAA8AAAB+AgoAGAAGAEUAAAAoQL4ACgAYAAcARQBFAAgA/QAKABgACQBKAEoAAAB+AgoAGQAAAEUAAAA3QP0ACgAZAAEARwBLAAAA/QAKABkAAgBHAEwAAAB+AgoAGQADAEYAAJifQP0ACgAZAAQARgAOAAAA/QAKABkABQBGAA8AAAB+AgoAGQAGAEUAAAAoQL4ACgAZAAcARQBFAAgA/QAKABkACQBKAE0AAAB+AgoAGgAAAEUAAAA4QP0ACgAaAAEARwBOAAAA/QAKABoAAgBHAE8AAAB+AgoAGgADAEYAAJifQP0ACgAaAAQARgAOAAAA/QAKABoABQBGAA8AAAB+AgoAGgAGAEUAAAAoQL4ACgAaAAcARQBFAAgA/QAKABoACQBKAFAAAAB+AgoAGwAAAEUAAAA5QP0ACgAbAAEARwBRAAAA/QAKABsAAgBHAFIAAAB+AgoAGwADAEYAAJifQP0ACgAbAAQARgAOAAAA/QAKABsABQBGAA8AAAB+AgoAGwAGAEUAAAAoQL4ACgAbAAcARQBFAAgA/QAKABsACQBKAFMAAAB+AgoAHAAAAEUAAAA6QP0ACgAcAAEARwBUAAAA/QAKABwAAgBHAFUAAAB+AgoAHAADAEYAAJifQP0ACgAcAAQARgAOAAAA/QAKABwABQBGAA8AAAB+AgoAHAAGAEUAAAAoQL4ACgAcAAcARQBFAAgA/QAKABwACQBKAFYAAAB+AgoAHQAAAEUAAAA7QP0ACgAdAAEARwBXAAAA/QAKAB0AAgBHAFgAAAB+AgoAHQADAEYAAJifQP0ACgAdAAQARgAOAAAA/QAKAB0ABQBGAA8AAAB+AgoAHQAGAEUAAAAoQL4ACgAdAAcARQBFAAgA/QAKAB0ACQBKAFkAAAB+AgoAHgAAAEUAAAA8QP0ACgAeAAEARwBaAAAA/QAKAB4AAgBHAFsAAAB+AgoAHgADAEYAAJifQP0ACgAeAAQARgAOAAAA/QAKAB4ABQBGAA8AAAB+AgoAHgAGAEUAAAAoQL4ACgAeAAcARQBFAAgA/QAKAB4ACQBKACoAAAD9AAoAHwAAAEgAXAAAAL4AFgAfAAEASABIAEgASABIAEgASABIAAgA1wBEAEwRAABsAigAKACMAH4AfgB+AH4AfgB+AH4AfgB+AH4AfgB+AH4AfgB+AH4AfgB+AH4AfgB+AH4AfgB+AH4AfgB+AH4ACAIQACAAAAABAC8CAAAAAEABDwAIAhAAIQAAAAEALwIAAAAAQAEPAAgCEAAiAAAAAQAvAgAAAABAAQ8ACAIQACMAAAABAC8CAAAAAEABDwDXAAwAUAAAADwAAAAAAAAAPgISALYGAAAAAEAAAAA8AAAAAAAAAIsIEACLCAAAAAAAAAAAAABkAAIAHQAPAAMDAAkAAAABAAMAHgAJCZkAAgAACeUAEgACAAAAAAAAAAgAHwAfAAAACABnCBcAZwgAAAAAAAAAAAAAAgAB/////wNEAAAKAAAACQgQAAAGEAC7Dc0HwYABAAYGAAALAhAAAAAAAAAAAAAAAAAAJQcAAA0AAgABAAwAAgBkAA8AAgABABEAAgAAABAACAD8qfHSTWJQP18AAgABACoAAgAAACsAAgAAAIIAAgABAIAACAAAAAAAAAAAACUCBAAAAB0BgQACAMEEFAAAABUAAACDAAIAAACEAAIAAAAmAAgAAAAAAAAA6D8nAAgAAAAAAAAA6D8oAAgAAAAAAAAA8D8pAAgAAAAAAAAA8D+hACIACQBkAAEAAQABAAYAAAAAAAAAAAAAAOA/AAAAAAAA4D8BAJwIJgCcCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAFUAAgAIAAACDgAAAAAAAAAAAAAAAAAAAD4CEgC2AAAAAABAAAAAPAAAAAAAAACLCBAAiwgAAAAAAAAAAAAAZAACAB0ADwADAAAAAAAAAQAAAAAAAACZAAIAAAlnCBcAZwgAAAAAAAAAAAAAAgAB/////wNEAAAKAAAACQgQAAAGEAC7Dc0HwYABAAYGAAALAhAAAAAAAAAAAAAAAAAAJQcAAA0AAgABAAwAAgBkAA8AAgABABEAAgAAABAACAD8qfHSTWJQP18AAgABACoAAgAAACsAAgAAAIIAAgABAIAACAAAAAAAAAAAACUCBAAAAB0BgQACAMEEFAAAABUAAACDAAIAAACEAAIAAAAmAAgAAAAAAAAA6D8nAAgAAAAAAAAA6D8oAAgAAAAAAAAA8D8pAAgAAAAAAAAA8D+hACIACQBkAAEAAQABAAYAAAAAAAAAAAAAAOA/AAAAAAAA4D8BAJwIJgCcCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAFUAAgAIAAACDgAAAAAAAAAAAAAAAAAAAD4CEgC2AAAAAABAAAAAPAAAAAAAAACLCBAAiwgAAAAAAAAAAAAAZAACAB0ADwADAAAAAAAAAQAAAAAAAACZAAIAAAlnCBcAZwgAAAAAAAAAAAAAAgAB/////wNEAAAKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAIAAAADAAAABAAAAAUAAAAGAAAABwAAAAgAAAAJAAAACgAAAP7///8MAAAADQAAAA4AAAAPAAAA/v///xEAAAASAAAAEwAAABQAAAAVAAAAFgAAAP7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8JCBAAAQYAD5oC1QcAAAAACAAAAPsPXgI/kqE6SwD///8BAADA/wAAAAAAAMD/AAAAAAAAwP8AAAAAAADA/wAAAAAAAMD/AAAAAAAAwP8AAAAAAADA/wAAAAAAAMD/AAAAAAAAwP8AAAAAAADA/wAAAAAAAMD/AAAAAAAAwP8AAAAAAADA/wAAAAAAAMD/AAAAAAAAwP8AAAAAAAAAAAAAAAEAAAAAAADgAAAAwP8A/v8AAADA/wAAAAEAAAAAAAAAAQAAAAAAAOAAAADA/wAA4AAAAMD/AAAAAQAAAAAAAOAAAADA/wAAAAAAAMD/AAAAAQAAAAAAAAAAAADA/wD+/wAAAAAAAADgAAAAwP8AAAAAAADA/wAAAAAAAMD/AAAAAAAAwP8AAAAAAADA/wD+HwAAAMD/AP4fAAAAwP8AAOAAAADA/wD+HwAAAMD/AADgAAAAwP8A/v8AAADA/wD+/wAAAMD/AP7/AAAAwP8AAOAA{\",\"timestamp\":1695745579863,\"status\":200,\"error\":\"OK\",\"message\":\"\",\"path\":\"/getArticleVideoList\"}}";

    @GetMapping(value = "getArticleVideoList")
    public ResultUtil getArticleVideoList() throws IOException {
        String file1Name = "file1";
        String file2Name = "file2";
        String filePath1 = "C:\\Users\\lenono\\Desktop\\123.doc";
        String filePath2 = "C:\\Users\\lenono\\Desktop\\123.txt";
        MultipartFile mockMultipartFile = new MockMultipartFile(file1Name, new FileInputStream(filePath1));
//        MultipartFile  mockMultipartFile1 = new MockMultipartFile(file2Name, new FileInputStream(filePath2));
//        ArrayList<MultipartFile> list = new ArrayList<>();
//        list.add(mockMultipartFile);
//        list.add(mockMultipartFile1);

        File file = new File("C:\\Users\\lenono\\Desktop\\1111.xls");
        FileInputStream in = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(in));

        return new ResultUtil(200,"请求成功",multipartFile);
    }

    @GetMapping(value = "getFile")
    @ApiOperation(value = "获取文件")
    public ResultUtil getFile() throws IOException {
        Request request = new Request.Builder().url("http://localhost:8080/getArticleVideoList").get().build();
        OkHttpClient build = new OkHttpClient().newBuilder().build();
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                byte[] bytes = response.body().bytes();
//                fileToBytes(bytes,"C:\\Users\\lenono\\Desktop\\","999.xls");
                System.out.println("********");
//                System.out.println(response.body().string());
                InputStream inputStream = response.body().byteStream();
                String collect = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining(System.lineSeparator()));
                byte[] bytes = testString.getBytes("UTF-8");
                fileToBytes(bytes,"C:\\Users\\lenono\\Desktop\\","999.xls");
                System.out.println(collect);
                System.out.println("********");
                JSONObject parseObject = JSON.parseObject(response.body().string());
            }
        });
        return null;
    }

    /**
     方法中传入的参数分别为
     filePath:为生成的file文件地址，地址后要以\（File.separator）结尾，
     fileName：为生成的file文件名称
     **/
    public static File fileToBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {

            file = new File(filePath + fileName);
            if (!file.getParentFile().exists()){
                //文件夹不存在 生成
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }


    public static void approvalFile( MultipartFile filecontent){
        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = filecontent.getInputStream();
            fileName = filecontent.getOriginalFilename();
            System.out.println(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = "C:\\Users\\lenono\\Desktop\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + "777.doc");
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}