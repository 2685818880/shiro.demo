package com.sojson.core.tags;

import java.io.IOException;
import java.util.Map;

import com.sojson.core.freemarker.utils.FreemarkerTagUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * 开发公司：WENYIFAN.NET <br/>
 * 版权：WENYIFAN.NET <br/>
 * <p>
 * 基础标签类
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2014年4月28日 　<br/>
 * <p>
 * *******
 * <p>
 * 
 * @author zhou-baicheng
 * 
 * @version 1.0,2014年4月28日 <br/>
 * 
 */
public abstract class WYFTemplateModel implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException
	{
		//DirectiveUtils是借用jeecms项目中的工具类，主要是因为它集成了一些异常处理功能，  
        //其实完全可以不用它，params是个Map，自己通过key取值就可以了，做一下空值判断
		/**
		 * 模版方法模式，把变化委派下去，交给子类实现！
		 */
		Map<String, TemplateModel> paramWrap = putValue(params);

		Map<String, TemplateModel> origMap = FreemarkerTagUtil.convertToTemplateModel(env, paramWrap);
		body.render(env.getOut());
		FreemarkerTagUtil.clearTempleModel(env, paramWrap, origMap);
	}

	/**
	 * 子类实现
	 * 
	 * @param params
	 * @return
	 * @throws TemplateModelException
	 */
	protected abstract Map<String, TemplateModel> putValue(Map<?, ?> params)
			throws TemplateModelException;
}
