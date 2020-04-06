package io.github.laomao1997;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import org.apache.commons.lang.StringUtils;

import java.awt.*;

public class HXTranslateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        Project project = e.getData(PlatformDataKeys.PROJECT);
        // TODO: 1. 获取用户选择到的内容
        // 获取当前用户所在的编辑器对象
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        // 通过编辑器得到用户选择文本的对象
        SelectionModel model = editor.getSelectionModel();
        // 获取模型中的文本
        String selectedText = model.getSelectedText();

        if (StringUtils.isEmpty(selectedText)) {
            return;
        }

        System.out.println("选中文本：" + selectedText + " 开始查询...");
        // TODO: 2. 查询

        // TODO: 3. 解析查询返回的结果

        // TODO: 4. 展示结果
        showPopup(editor, selectedText);

    }

    private void showPopup(Editor editor, String selectedText) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                BalloonBuilder builder = factory.createHtmlTextBalloonBuilder(selectedText, null,
                        new JBColor(new Color(188, 238, 188), new Color(73, 120, 73)), null);

                builder.setFadeoutTime(5000) // 无操作5秒后隐藏
                        .createBalloon() // 创建气泡
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below); // 指定位置显示气泡
            }
        });
    }
}
