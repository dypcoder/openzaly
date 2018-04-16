package com.akaxin.admin.controller;

import static com.akaxin.proto.core.ConfigProto.ConfigKey.GROUP_MEMBERS_COUNT_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.LOG_LEVEL_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.PIC_PATH_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.PIC_SIZE_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.PUSH_CLIENT_STATUS_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.REGISTER_WAY_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_ADDRESS_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_HTTP_ADDRESS_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_HTTP_PORT_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_INTRODUCTION_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_LOGO_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_MANAGER_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_NAME_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.SITE_PORT_VALUE;
import static com.akaxin.proto.core.ConfigProto.ConfigKey.U2_ENCRYPTION_STATUS_VALUE;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.akaxin.admin.service.IBasicService;

@Controller
@RequestMapping("manage")
public class BasicManageController {
	private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

	@Autowired
	private IBasicService manageService;

	@RequestMapping("/index")
	public String homePage() {
		return "index";
	}

	// 获取站点配置信息
	@RequestMapping("/basicConfig")
	public ModelAndView toBasic() {
		ModelAndView modelAndView = new ModelAndView("platform/basic/setBasic");
		Map<String, Object> model = modelAndView.getModel();
		Map<Integer, String> map = manageService.getSiteConfig();
		Set<Integer> integers = map.keySet();
		String site_prot = "";
		String site_address = "";
		String http_prot = "";
		String http_address = "";
		for (Integer integer : integers) {
			String res = map.get(integer);
			switch (integer) {
			case SITE_NAME_VALUE:
				model.put("site_name", res);
				break;
			case SITE_ADDRESS_VALUE:
				site_address = res;
				break;
			case SITE_PORT_VALUE:
				site_prot = res;
				break;
			case SITE_HTTP_ADDRESS_VALUE:
				http_address = res;
				break;
			case SITE_HTTP_PORT_VALUE:
				http_prot = res;
				break;
			case SITE_LOGO_VALUE:
				model.put("site_logo", res);
				break;
			case SITE_INTRODUCTION_VALUE:
				model.put("site_desc", res);
				break;
			case REGISTER_WAY_VALUE:
				model.put("site_register_way", res);
				break;
			case PIC_SIZE_VALUE:
				model.put("pic_size", res);
				break;
			case PIC_PATH_VALUE:
				model.put("pic_path", res);
				break;
			case GROUP_MEMBERS_COUNT_VALUE:
				model.put("group_members_count", res);
				break;
			case U2_ENCRYPTION_STATUS_VALUE:
				model.put("u2_encryption_status", res);
				break;
			case PUSH_CLIENT_STATUS_VALUE:
				model.put("push_client_status", res);
				break;
			case LOG_LEVEL_VALUE:
				model.put("log_level", res);
				break;
			case SITE_MANAGER_VALUE:
				model.put("subgenus_admin", res);
				break;
			}

		}
		model.put("siteAddressAndPort", site_address + ":" + site_prot);
		model.put("httpAddressAndPort", http_address + ":" + http_prot);
		return modelAndView;
	}

	// 更新站点配置信息
	@RequestMapping("/updateConfig")
	public boolean updateBasicConfig(ModelAndView modelAndView) {
		// #TODO 判断权限

		return true;
	}

}