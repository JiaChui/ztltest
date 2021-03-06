package org.zkoss.zktest.test2.theme

import org.zkoss.zstl.ZTL4ScalaTestCase
import org.zkoss.ztl.Tags
import org.zkoss.ztl.Tags

@Tags(tags = "IOS,VisionTest")
class IVT_Fisheyebar_Vert_Test extends ZTL4ScalaTestCase {
	def testClick() = {
		val zscript = """
<fisheyebar id="fsb" attachEdge="top" orient="vertical" 
	itemWidth="80" itemHeight="80" itemMaxHeight="160" itemMaxWidth="160">
	<fisheye image="/img/Centigrade-Widget-Icons/FolderABlue-128x128.png" label="Folder" />
	<fisheye image="/img/Centigrade-Widget-Icons/ReadingGlass-128x128.png" label="Reading Glasses" />
	<fisheye image="/img/Centigrade-Widget-Icons/Briefcase-128x128.png" label="Project" />
	<fisheye image="/img/Centigrade-Widget-Icons/MailboxFlag-128x128.png" label="Email" />
	<fisheye image="/img/Centigrade-Widget-Icons/Globe-128x128.png" label="Globe" />
	<fisheye image="/img/Centigrade-Widget-Icons/Spyglass-128x128.png" label="Spyglass" />
</fisheyebar>
		""";

		runZTL(zscript,
			() => {
				verifyImage();
			});
	}
}
