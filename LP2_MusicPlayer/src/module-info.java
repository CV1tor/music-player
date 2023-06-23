module LP2_MusicPlayer {
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires jlayer;
	
	exports com.project;
	
	opens application to javafx.graphics, javafx.fxml;
	opens com.project.controller to javafx.fxml;
}
