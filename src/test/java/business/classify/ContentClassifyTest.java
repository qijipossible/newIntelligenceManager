package business.classify;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import java.io.IOException;

public class ContentClassifyTest {

    public static void main(String[] args) throws IOException {
        ContentClassify.predict("想找路时，掏出手机就可进入三维导航模式；出行时，再也不用过金属安检门，一个小仪器就能完成扫描……或许5年以后，普通四川市民的生活，都会随着一个个军民融合成果的落地，发生翻天覆地的变化。\n" +
                "　　7月13日下午，四川省政府新闻办举行“四川省系统推进全面创新改革试验”第二场发布会，省政府副秘书长罗治平、省经信委主任陈新有、省国防科工办主任许州、省发改委副主任雷开平、省科技厅副厅长田云辉共同回答了记者关心的问题。\n" +
                "　　会上透露，四川拟用5年时间，推动军民融合主营业务收入突破4000亿元，培育10家100亿以上、20家50亿以上的军民融合大企业大集团，初步建成具有世界先进水平的国防军工战略基地、军地优势资源共享转化示范区、军民两用技术协同创新先导区、军民融合高端产业发展集聚区。\n" +
                "　　设立100亿基金，58家单位组建产业联盟\n" +
                "　　推进军民融合，四川既有政策支持，还有“福利”派送。\n" +
                "　　罗治平介绍，今年4月，四川率先在全国设立了省级军民融合产业发展基金，总规模达到100亿元。目前，省级财政先期投入的10亿元已经到位。\n" +
                "　　值得一提的是，目前四川已面向全省军民融合重点企事业单位征集了首批41个符合条件的项目，总投资达174亿，提出基金需求超40亿元，初步建立了基金项目库。\n" +
                "　　除了设立发展基金，多个国防科工企业、大学也开始迈出军民融合步伐。罗治平介绍，四川已率先在全国成立了省级军民融合高技术联盟，以四川九洲集团为发起单位，中航工业成飞公司、四川大学等58家单位共同组建，搭建了政、产、学、研、用平台，打通了行业壁垒和垄断。同时，四川还率先与国家国防科工局和所有央属军工集团建立了战略合作关系，力争引入一批重大项目落地。\n" +
                "　　5年突破4000亿元，200个军转民项目将启动\n" +
                "　　简单来说，四川的军民融合，就是打开“军转民”和“民参军”这两条通道。\n" +
                "　　具体来看，四川要推动军工企业和科研院所改革，目前已制定了3家重点军工科研院所转企改革、4家军工企业股份制改造或公司化改制、3家军工单位后勤社会化改革计划。\n" +
                "　　在创新军工科技成果转化方面，将建立军用技术再研发项目库，启动200个军用技术再研发转民用项目，通过省财政专项资金引导支持。目前，四川已向11个市（州）征集并梳理认定了50户军民融合型大企业集团和50户军民融合创新型企业，正在研究制定培育方案。\n" +
                "　　此外，四川力争用5年时间，推动军民融合主营业务收入突破4000亿元，培育10家100亿以上、20家50亿以上的军民融合大企业大集团，初步建成具有世界先进水平的国防军工战略基地、军地优势资源共享转化示范区、军民两用技术协同创新先导区、军民融合高端产业发展集聚区。\n" +
                "　　筹建产业研究院，帮助中小企业技术革新\n" +
                "　　如何实现上述目标？罗治平介绍，事实上，四川的军民深度融合已呈现出良好势头。去年，国家国防科工局组织的“军工集团重点合作意愿省份”摸底调查中，四川排名全国第一。同时，四川也建成了一批具有一定规模的军民融合产业集聚区，具备较强的产业承载和基础配套能力。目前，四川的军民融合产业经济规模收入已达2660亿元，稳居全国第二位。其中，核技术、航空、航天、电子信息4大类高新技术产值占比达到73%。\n" +
                "　　而在未来，四川还将打破“军转民、民参军”双向互动通道，推动构建军民融合发展领导体制和工资机制，发展信息安全、航空发动机等军民融合产业园区和基地，加快形成具有核心竞争力的军民融合产业集群，形成一批可复制、可推广、有特色、有影响的制度性经验成果。\n" +
                "　　记者了解到，四川很多大企业大集团的军民融合能力较强，但很多中小企业却信息局限，创新资源匮乏。这些企业如何进行军民融合？陈新有介绍，四川正在筹建产业技术研究院，不仅有顶层的研究，还有专业的创新平台，“这些机构，都会帮助中小企业进行技术革新”。同时，四川正在推动180多个县的园区公共服务平台建设，针对其创新、人才的建设，提供完备的服务体系。\n" +
                "|焦点问答|\n" +
                "　　1、“民参军”门槛高不高？在四川“参军”渠道总体通畅　　　现阶段民营企业参与军工建设，是否依然面对很多困难？最大的障碍在哪里？　　　“‘民参军’有一些困难是实际情况，但是不能简单说门槛高。”许州表示，虽然存在强调既往有承担军品任务资历和军品市场准入资质程序较复杂、行政审批周期较长等问题，但是四川的“民参军”渠道整体来说比较通畅。　　　在陈新有看来，“民参军”是两种机制。第一种是企业为军工集团做配套，这个协调可能相对容易一些。另一种是直接作为军方采购对象，这个难度可能大一些，路径更长一些，“军民融合上升为国家战略后，各大军工集团在下一步转型升级中，都确定了‘小核心、大协作’的发展战略，这就为中小企业、民营企业创造出一个非常好的发展前景。”　　　而当前军民深度融合的最大障碍，罗治平认为是体制和机制问题。不过四川获批成为全面改革创新试验区域之一，核心任务就是要探索推进军民融合改革创新，为解决体制性等问题探索路径，积累经验。现阶段，四川正在充分利用与国防科工局、各军工集团签署战略合作协议的有利条件，从战略层面、技术层面、工作层面，协调各军工集团公司支持在川军工单位积极参与四川全面创新改革试验。　　　2、“民参军”如何推动？推动军方在川设采购分中心　　　“‘民参军’不是一蹴而就的，一些企业要早做工作，多咨询。”民营企业如何更好地“参军”？许州认为应该提前做准备，提升自身能力。　　　“为了鼓励地方优势民营企业加快进入武器装备科研生产领域，四川将重点推进以下几项工作。”许州说，一是推动军方在川建立武器装备采购服务四川分中心，开通武器装备采购信息网四川查询点；二是建立完善军民科技资源共享机制和创新体系，完善四川军工大型仪器设备共享服务平台；三是加强绵阳科技城军民两用技术交易中心和技术转移中心建设；四是与国家有关部门积极沟通，争取建立社会资本到军品科研生产特别是前期技术研发阶段的体制机制。　　　许州强调，不是说所有民营企业都可以“参军”，有能力有技术的民营企业，才鼓励“民参军”。　　　3、100亿基金如何用？年底前完成第一批项目投资　　　四川率先在全国设立了总规模达100亿元的军民融合产业发展基金。这100亿元如何使用？　　　“当前军工单位受体制机制约束，不能充分融入市场竞争，而‘民参军’这个方面，民口单位又受到准入门槛的限制。”许州说，为了调动在川军工单位参与军民融合改革发展的积极性，基金将主要投向四大领域：第一，支持军用技术转移转化；第二，支持相关民营企业加快技术改造；第三，支持军工能力专业化重组；第四，支持参与符合省委、省政府发展战略的特色军民融合产业基地和项目建设。　　　许州表示，目前军民融合产业基金即将进入操作层面，目标是在今年年底前完成第一批项目投资，“目前已经征集到41个符合基金投资方向要素要求的项目，将来会有基金托管机构和投资决策委员会等专业机构对项目进行把关、考察、考核，现在我们所做的工作主要是把好的项目筛选出来，为后续工作做好前期准备。”　|关注|　　　微波炉GPS等都是军民融合产物　　　军民融合与普通市民有没有关系？当然有。　　　从我们每天用来热菜热饭的微波炉，再到开车、跑步用的GPS导航……军民融合的成果与产品，一直被广泛运用在日常生活中。　　　陈新有透露，四川的军民融合优势突出，比如核技术就可转化为核医学，并用在医疗救助上，“市场很大，四川也开始启动研究”。记者了解到，核医学是采用核技术来诊断、治疗和研究疾病的一门新兴学科，并已在欧美发展为成熟产业。据核医学行业初步统计，美国每年核医学产值约为500亿美元，而中国目前只有约100亿元人民币。　　　影响这个行业在国内发展的因素，主要是技术门槛。目前国内的核医学设备绝大部分为进口，价格高昂。以一套癌症早期诊断的PET-CT为例，包括质子加速器和显像仪器在内，往往高达两三千万元。　　　除了能帮助治病的核医疗外，四川还在推动智慧城市云平台和激光三维扫描技术的研究，这些从军工企业转化的技术一旦研发完成，将改变每一个市民的生活。“整个社会将进入三维互联网时代，市民通过手机、平板电脑等，就能实时进行三维导航。”　　　此外，陈新有还透露，在信息安全方面，四川正在研究“太赫兹人体安检仪”。它不同于目前国内机场使用的金属探测门和X射线探测仪，它能检测出乘客随身携带的非金属危险品，如陶瓷刀、工程塑料手枪、有机炸药，进一步提升了安检等级，“现在，民航管理机构对该产品也很感兴趣”。\n" +
                "主办单位：国家国防科技工业局地址：北京市海淀区阜成路甲8号邮编：100048承办单位：国家国防科技工业局新闻宣传中心信息报送邮箱：webmaster@sastind.gov.cn国家国防科技工业局版权所有京ICP备11007804号\n");
    }
}

